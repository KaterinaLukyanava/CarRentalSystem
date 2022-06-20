package com.carrental.dao.repository;

import com.carrental.dao.model.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends CrudRepository<Car,Integer> {
    List<Car> findAll();
    void deleteById(Integer id);

}

