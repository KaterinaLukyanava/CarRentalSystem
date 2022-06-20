package com.carrental.controller;


import com.carrental.dao.model.Car;
import com.carrental.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CarsController {
    private final CarService carService;

    public CarsController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public String getAllCars(Model model) {
        List<Car> allCar = carService.findAllCar();
        model.addAttribute("cars", allCar);
        return "car";
    }

    @GetMapping("/addCar")
    public String addCar(Model model) {
        model.addAttribute("car",new Car());
        return "newCar";
    }

    @PostMapping("/addCar")
    public String addNewCar(@Valid Car car,Errors errors){
        if(errors.hasErrors())
            return "addCar";
        carService.save(car);
        return "redirect:/cars";

    }

    @GetMapping("/carDelete/{id}")
    public String deleteCar(@PathVariable("id") Integer id){
        carService.deleteById(id);
        return "redirect:/cars";
    }
}
