package com.sarah.pelangganservice.pelangganservice.repository;

import com.sarah.pelangganservice.pelangganservice.model.Pelanggan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PelangganRepository extends JpaRepository<Pelanggan, Long> {
}
