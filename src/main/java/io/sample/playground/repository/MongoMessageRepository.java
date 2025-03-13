package io.sample.playground.repository;

import io.sample.playground.dto.MongoMessageDTO;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface MongoMessageRepository extends MongoRepository<MongoMessageDTO, String> {
}
