package com.sarah.peminjamanservice.peminjamanservice.repository;

import com.sarah.peminjamanservice.peminjamanservice.model.PinjamanReadModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PinjamanReadModelRepository extends MongoRepository<PinjamanReadModel, Long> {
}