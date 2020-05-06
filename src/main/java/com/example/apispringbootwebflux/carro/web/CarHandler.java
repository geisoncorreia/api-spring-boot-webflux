package com.example.apispringbootwebflux.carro.web;

import com.example.apispringbootwebflux.carro.CarManager;
import com.example.apispringbootwebflux.carro.model.CarDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
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
}
