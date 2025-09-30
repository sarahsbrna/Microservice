package com.sarah.peminjamanservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sarah.peminjamanservice.model.Peminjaman;
import com.sarah.peminjamanservice.repository.PeminjamanRepository;
import com.sarah.peminjamanservice.vo.Anggota;
import com.sarah.peminjamanservice.vo.Buku;
import com.sarah.peminjamanservice.vo.ResponseTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class PeminjamanService {

    @Autowired
    private PeminjamanRepository repo;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    // ===== CRUD Lokal =====
    public List<Peminjaman> getAll() {
        return repo.findAll();
    }

    public Peminjaman save(Peminjaman peminjaman) {
        return repo.save(peminjaman);
    }

    public Optional<Peminjaman> getById(Long id) {
        return repo.findById(id);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    // ===== Integrasi dengan Microservices lain =====
    public ResponseTemplate getPeminjamanWithDetail(Long id) {
        Optional<Peminjaman> opt = repo.findById(id);
        if (opt.isEmpty()) {
            return null; // atau throw exception
        }

        Peminjaman peminjaman = opt.get();

        // === Cari instance Anggota-Service ===
        ServiceInstance anggotaInstance = discoveryClient.getInstances("ANGGOTA-SERVICE").get(0);
        Anggota anggota = restTemplate.getForObject(
                anggotaInstance.getUri() + "/api/anggota/" + peminjaman.getAnggotaId(),
                Anggota.class
        );

        // === Cari instance Buku-Service ===
        ServiceInstance bukuInstance = discoveryClient.getInstances("BUKU-SERVICE").get(0);
        Buku buku = restTemplate.getForObject(
                bukuInstance.getUri() + "/api/buku/" + peminjaman.getBukuId(),
                Buku.class
        );

        // Gabungkan hasil
        ResponseTemplate vo = new ResponseTemplate();
        vo.setPeminjaman(peminjaman);
        vo.setAnggota(anggota);
        vo.setBuku(buku);

        return vo;
    }
}
