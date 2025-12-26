package com.sarah.peminjamanservice.peminjamanservice.repository;

import com.sarah.peminjamanservice.peminjamanservice.model.Pinjaman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PinjamanRepository extends JpaRepository<Pinjaman, Long> {
}