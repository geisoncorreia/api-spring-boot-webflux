package com.example.apispringbootwebflux.carro.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityScan
@Table("car")
public class Car {

    @Id
    private UUID id;
    private Integer ano;
    private String cor;
}
