package com.sarah.peminjamanservice.peminjamanservice.service;

import com.sarah.peminjamanservice.peminjamanservice.model.PinjamanReadModel;
import com.sarah.peminjamanservice.peminjamanservice.repository.PinjamanReadModelRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PinjamanQueryService {

    private final PinjamanReadModelRepository readModelRepository;

    public PinjamanQueryService(PinjamanReadModelRepository readModelRepository) {
        this.readModelRepository = readModelRepository;
    }

    public List<PinjamanReadModel> getAllPinjaman() {
        return readModelRepository.findAll();
    }

    public Optional<PinjamanReadModel> getPinjamanById(Long id) {
        return readModelRepository.findById(id);
    }
}
