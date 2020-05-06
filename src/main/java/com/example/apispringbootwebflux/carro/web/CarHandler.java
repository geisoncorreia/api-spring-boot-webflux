package com.example.apispringbootwebflux.carro.web;

import com.example.apispringbootwebflux.carro.CarManager;
import com.example.apispringbootwebflux.carro.CarMapper;
import com.example.apispringbootwebflux.carro.model.Car;
import com.example.apispringbootwebflux.carro.model.CarDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;
import static org.springframework.web.reactive.function.server.ServerResponse.*;

@Component
@RequiredArgsConstructor
public class CarHandler {

    private final CarManager carManager;

    public Mono<ServerResponse> get(ServerRequest request) {
        final UUID id = UUID.fromString(request.pathVariable("id"));
        final Mono<CarDto> car = carManager.findById(id);
        return car
            .flatMap(p -> ok().contentType(APPLICATION_JSON).body(fromPublisher(car, CarDto.class)))
            .switchIfEmpty(notFound().build());
    }

    public Mono<ServerResponse> all(ServerRequest request) {
        return ok().contentType(APPLICATION_JSON)
            .body(fromPublisher(carManager.all(), CarDto.class))
                .switchIfEmpty(notFound().build());
    }

    public Mono<ServerResponse> put(ServerRequest request) {
        final UUID id = UUID.fromString(request.pathVariable("id"));
        final Mono<CarDto> car = request.bodyToMono(CarDto.class);
        return carManager
                .findById(id)
                .flatMap(
                        old ->
                            ok().contentType(APPLICATION_JSON)
                                    .body(
                                        fromPublisher(
                                                car
                                                    .map(p -> new CarDto(p, id))
                                                    .flatMap(c -> carManager.update(old, c)),
                                                CarDto.class)))
                .switchIfEmpty(notFound().build());
    }

    public Mono<ServerResponse> post(ServerRequest request) {
        final Mono<CarDto> car = request.bodyToMono(CarDto.class);
        final UUID id = UUID.randomUUID();
        return created(UriComponentsBuilder.fromPath("car/" + id).build().toUri())
                .contentType(APPLICATION_JSON)
                .body(
                        fromPublisher(
                                car.map(p -> new CarDto(p, id)).flatMap(carManager::save), CarDto.class));
    }

    public Mono<ServerResponse> delete(ServerRequest request) {
        final UUID id = UUID.fromString(request.pathVariable("id"));
        return carManager
                .findById(id)
                .flatMap(p -> noContent().build(carManager.delete(p)))
                .switchIfEmpty(notFound().build());
    }

}
