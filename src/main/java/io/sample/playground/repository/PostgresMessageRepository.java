package io.sample.playground.repository;

import io.sample.playground.dto.PostgresMessageDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostgresMessageRepository extends JpaRepository<PostgresMessageDTO, String> {
}
