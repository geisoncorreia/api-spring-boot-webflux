package com.example.apispringbootwebflux.carro.model;

import com.fasterxml.jackson.annotation.JacksonAnnotation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarDto {

    private UUID id;
    private Integer ano;
    private String cor;
}
