package com.example.exercise101;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @PostMapping
    public Car createCar(@RequestBody Car car) {
        return carRepository.save(car);
    }

    @GetMapping
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Car> getCarById(@PathVariable Long id) {
        if(carRepository.existsById(id)){
            Optional<Car> car = carRepository.findById(id);
            return car;
        }
        return null;
    }

    @PutMapping("/{id}")
    public Car updateCarType(@PathVariable Long id, @RequestBody String type) {
        if (carRepository.existsById(id)) {
            Car car = carRepository.findById(id).get();
            car.setType(type);
            Car updatedCar = carRepository.save(car);
            return updatedCar;
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
        } else {
            System.out.println("HttpStatus.CONFLICT");
        }
    }

    @DeleteMapping("/deleteAll")
    public void deleteAllCars() {
        carRepository.deleteAll();
    }
}
