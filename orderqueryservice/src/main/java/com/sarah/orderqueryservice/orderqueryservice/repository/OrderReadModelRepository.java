package com.sarah.orderqueryservice.orderqueryservice.repository;

import com.sarah.orderqueryservice.orderqueryservice.model.OrderReadModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderReadModelRepository extends MongoRepository<OrderReadModel, Long> {
}