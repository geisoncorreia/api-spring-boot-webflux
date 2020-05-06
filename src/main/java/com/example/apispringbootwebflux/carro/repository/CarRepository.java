package com.example.apispringbootwebflux.carro.repository;

import com.example.apispringbootwebflux.carro.model.Car;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CarRepository extends ReactiveCrudRepository<Car, UUID> {
}
