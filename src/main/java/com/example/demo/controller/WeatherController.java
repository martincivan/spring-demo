package com.example.demo.controller;

import com.example.demo.service.CityService;
import com.example.demo.service.WeatherService;
import com.example.demo.view.TemperatureResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {
    private final WeatherService weatherService;
    private final CityService cityService;

    public WeatherController(WeatherService weatherService, CityService cityService) {
        this.weatherService = weatherService;
        this.cityService = cityService;
    }

    @CrossOrigin("*")
    @GetMapping("/{cityName}")
    public TemperatureResponse getWeather(@PathVariable String cityName) {
        var city = cityService.getCity(cityName);
        if (city == null) {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
        }
        var weather = weatherService.getWeather(city);
        return new TemperatureResponse(weather.getHourly().getTime(), weather.getHourly().getTemperature_2m());
    }
}
