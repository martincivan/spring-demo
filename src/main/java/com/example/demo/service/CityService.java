package com.example.demo.service;

import com.example.demo.model.City;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CityService {
    private Map<String, City> cityNameMap = new HashMap<>();

    public CityService() {
        cityNameMap.put("Bratislava", new City(48.1482, 17.1067, "Bratislava"));
        cityNameMap.put("Vienna", new City(48.2085, 16.3721, "Vienna"));
        cityNameMap.put("Prague", new City(50.088, 14.4208, "Prague"));
        cityNameMap.put("Budapest", new City(47.4984, 19.0404, "Budapest"));
    }

    public City getCity(String cityName) {
        return cityNameMap.get(cityName);
    }

    public Collection<City> getAllCities() {
        return cityNameMap.values();
    }
}
