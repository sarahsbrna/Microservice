package com.sarah.peminjamanqueryservice.peminjamanqueryservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sarah.peminjamanqueryservice.peminjamanqueryservice.model.PeminjamanView;
import com.sarah.peminjamanqueryservice.peminjamanqueryservice.repository.PeminjamanViewRepository;

import java.util.List;

@RestController
@RequestMapping("/api/peminjaman")
public class PeminjamanQueryController {

    private final PeminjamanViewRepository repository;

    public PeminjamanQueryController(PeminjamanViewRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<PeminjamanView> getAllPeminjaman() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PeminjamanView> getPeminjamanById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}