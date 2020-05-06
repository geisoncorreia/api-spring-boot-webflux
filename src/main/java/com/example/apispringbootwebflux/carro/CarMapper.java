package com.example.apispringbootwebflux.carro;

import com.example.apispringbootwebflux.carro.model.Car;
import com.example.apispringbootwebflux.carro.model.CarDto;

public class CarMapper {

    private CarMapper() {}

    static Car toCar(CarDto carDto) {
        return new Car(
                carDto.getId(),
                carDto.getAno(),
                carDto.getCor()
        );
    }

    static CarDto toCarDto(Car car) {
        return new CarDto(
                car.getId(),
                car.getAno(),
                car.getCor()
        );
    }
}
