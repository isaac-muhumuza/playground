package io.sample.playground.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "messages")
@Data
@NoArgsConstructor
public class PostgresMessageDTO {

    @Id
    private @NotNull @Size(
            min = 3,
            max = 65
    ) String id = UUID.randomUUID().toString();

    private String name;

    private String details;

}
