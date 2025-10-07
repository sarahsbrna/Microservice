package com.sarah.anggotaservice.anggotaservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sarah.anggotaservice.anggotaservice.model.Anggota;
import com.sarah.anggotaservice.anggotaservice.repository.AnggotaRepository;

import java.util.List;

@Service
public class AnggotaService {
    @Autowired
    private AnggotaRepository anggotaRepository;

    public Anggota save(Anggota anggota) {
        return anggotaRepository.save(anggota);
    }

    public Anggota findById(Long id) {
        return anggotaRepository.findById(id).orElse(null);
    }

    public List<Anggota> findAll() {
        return anggotaRepository.findAll();
    }
}