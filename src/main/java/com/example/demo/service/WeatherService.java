package com.example.demo.service;

import com.example.demo.model.City;
import com.example.demo.weatherclient.WeatherResponse;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.concurrent.TimeUnit;

@Service
public class WeatherService {
    private final RestTemplate restTemplate;
    private final String weatherApiUrlFormat = "https://api.open-meteo.com/v1/forecast?hourly=temperature_2m";

    public WeatherService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @Cacheable("weather")
    public WeatherResponse getWeather(City city) {
        var builder = UriComponentsBuilder.fromHttpUrl(weatherApiUrlFormat).queryParam("latitude", city.getLatitude()).queryParam("longitude", city.getLongitude());
        return restTemplate.getForObject(builder.build().toUriString(), WeatherResponse.class);
    }

    @CacheEvict(allEntries = true, value = {"weather"})
    @Scheduled(fixedDelay = 10, timeUnit = TimeUnit.MINUTES)
    public void reportCacheEvict() {
        System.out.println("Flush Cache");
    }
}
