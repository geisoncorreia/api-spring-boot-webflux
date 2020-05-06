package com.example.apispringbootwebflux.carro;

import com.example.apispringbootwebflux.carro.model.CarDto;
import com.example.apispringbootwebflux.carro.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CarManager {

    private final CarRepository carRepository;
    public Flux<CarDto> all() {
        return carRepository.findAll().map(CarMapper::toCarDto);
    }

    public Mono<CarDto> findById(final UUID id) {
        return carRepository.findById(id).map(CarMapper::toCarDto).switchIfEmpty(Mono.empty());
    }

    public Mono<CarDto> save(CarDto carDto) {
        return Mono.fromSupplier(
                () -> {
                    carRepository
                            .save(CarMapper.toCar(carDto))
                            .subscribe();
                    return carDto;
                });
    }

    public Mono<CarDto> update(CarDto old, CarDto updated) {
        return Mono.fromSupplier(
                () -> {
                    carRepository
                            .save(CarMapper.toCar(updated))
                            .subscribe();
                    return updated;
                });
    }

    public Mono<Void> delete(CarDto carDto) {
        return Mono.fromSupplier(
                () -> {
                    carRepository
                            .delete(CarMapper.toCar(carDto))
                            .subscribe();
                    return null;
                });
    }


}
