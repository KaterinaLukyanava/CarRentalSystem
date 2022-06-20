package com.carrental.service;

import com.carrental.dao.model.Car;

import java.util.List;

public interface CarService {
    List<Car> findAllCar();
    void save(Car car);
    void deleteById(Integer id);


}
