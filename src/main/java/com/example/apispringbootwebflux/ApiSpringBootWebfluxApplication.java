package com.example.apispringbootwebflux;

import com.example.apispringbootwebflux.carro.model.Car;
import lombok.var;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;

import java.util.UUID;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@SpringBootApplication
public class ApiSpringBootWebfluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiSpringBootWebfluxApplication.class, args);
    }

    /*@Bean
    RouterFunction<ServerResponse> routes(ReservationRepository rr) {
        return route()
                .GET("/reservations", r -> ok().body(rr.findAll(), Car.class))
                .GET("/hello", r -> ok().bodyValue("Hi, Zupers!"))
                .build();
    }

//    @Bean
    ApplicationRunner runner(ReservationRepository reservationRepository) {
        return args -> {

            var data = Flux
                    .just(null, 2020, "Azul")
                    .map(name -> new Car(null, 2020, "Azul") {
                    })
                    .flatMap(reservationRepository::save);

            reservationRepository
                    .deleteAll()
                    .thenMany(data)
                    .thenMany(reservationRepository.findAll())
                    .subscribe(System.out::println);
        };
    }*/

}

/*
interface ReservationRepository extends ReactiveCrudRepository<Car, UUID> {

}
*/
