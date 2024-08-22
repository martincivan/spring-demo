package com.example.demo.controller;

import com.example.demo.model.City;
import com.example.demo.service.CityService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/city")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @CrossOrigin("*")
    @GetMapping("/all")
    public Collection<String> getAll() {
        return cityService.getAllCities().stream().map(City::getName).sorted().collect(Collectors.toList());
    }
}
