package com.sarah.peminjamanservice.peminjamanservice.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sarah.peminjamanservice.peminjamanservice.dto.PeminjamanEventDTO;
import com.sarah.peminjamanservice.peminjamanservice.model.Peminjaman;
import com.sarah.peminjamanservice.peminjamanservice.repository.PeminjamanRepository;
import com.sarah.peminjamanservice.peminjamanservice.vo.Anggota;
import com.sarah.peminjamanservice.peminjamanservice.vo.Buku;

import java.time.LocalDate;

/**
 * Ini adalah Command Service dalam pola CQRS.
 * Tugasnya HANYA untuk menangani operasi tulis (Create, Update, Delete).
 * Setelah berhasil menyimpan data ke database SQL, ia akan menerbitkan
 * sebuah event ke RabbitMQ.
 */
@Service
public class PeminjamanService {

    private final PeminjamanRepository peminjamanRepository;
    private final RestTemplate restTemplate;
    private final RabbitTemplate rabbitTemplate;

    @Value("${app.rabbitmq.exchange}")
    private String exchange;
    @Value("${app.rabbitmq.routingkey}")
    private String routingKey;

    @Autowired
    public PeminjamanService(PeminjamanRepository peminjamanRepository, RestTemplate restTemplate,
            RabbitTemplate rabbitTemplate) {
        this.peminjamanRepository = peminjamanRepository;
        this.restTemplate = restTemplate;
        this.rabbitTemplate = rabbitTemplate;
    }

    public Peminjaman save(Peminjaman peminjaman) {
        // 1. Validasi Anggota dan Buku dari service lain
        Anggota anggota = restTemplate.getForObject("http://anggotaservice/api/anggota/" + peminjaman.getAnggotaId(),
                Anggota.class);
        Buku buku = restTemplate.getForObject("http://bukuservice/api/buku/" + peminjaman.getBukuId(), Buku.class);

        // 2. LOGIKA BARU: Validasi dan Kalkulasi Tanggal
        // Memastikan pengguna mengirimkan tanggal pinjam dalam request body.
        if (peminjaman.getTanggalPinjam() == null) {
            throw new IllegalArgumentException("Tanggal Pinjam harus diisi!");
        }

        // Menghitung tanggal kembali berdasarkan tanggal pinjam yang diberikan
        // pengguna.
        peminjaman.setTanggalKembali(peminjaman.getTanggalPinjam().plusDays(7));

        // 3. Simpan data peminjaman ke database SQL (Write Database)
        Peminjaman savedPeminjaman = peminjamanRepository.save(peminjaman);

        // 4. Buat Event Data Transfer Object (DTO)
        // DTO ini berisi semua data yang dibutuhkan oleh service lain (query dan
        // notifikasi)
        PeminjamanEventDTO eventDTO = new PeminjamanEventDTO(
                savedPeminjaman.getId(),
                anggota.getNama(),
                anggota.getEmail(),
                buku.getJudul(),
                savedPeminjaman.getTanggalPinjam(),
                savedPeminjaman.getTanggalKembali());

        // 5. Kirim event ke RabbitMQ
        rabbitTemplate.convertAndSend(exchange, routingKey, eventDTO);
        System.out.println("Peminjaman CREATED event sent to RabbitMQ: " + eventDTO);

        return savedPeminjaman;
    }
}