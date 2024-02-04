package org.example.controller;

import org.example.entity.City;
import org.example.mapper.JsonMapper;
import org.example.service.CityService;

import java.util.List;

public class CityController {
    private final CityService cityService;
    private final JsonMapper jsonMapper;

    public CityController(CityService cityService, JsonMapper jsonMapper) {
        this.cityService = cityService;
        this.jsonMapper = jsonMapper;
    }

    public String addCityFromJson(String jsonRequest) {
        try {
            City city = jsonMapper.mapCityFromJson(jsonRequest);
            cityService.addCity(city);
            return jsonMapper.mapCityToJson(city);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error processing JSON request: ", e);
        }
    }

    public void deleteCity(long id) {
        City cityToDelete = cityService.getCityById(id);
        cityService.deleteCity(cityToDelete);
    }

    public City getCityById(long id) {
        return cityService.getCityById(id);
    }

    public List<City> getAllCity() {
        return cityService.getAllCity();
    }
}
