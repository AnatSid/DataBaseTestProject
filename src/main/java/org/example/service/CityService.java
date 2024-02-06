package org.example.service;

import org.example.dao.CityDao;
import org.example.entity.City;

import java.util.List;

public class CityService {
    private final CityDao cityDao;

    public CityService(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    public City addCity(City city) {
        if (city == null) {
            throw new IllegalArgumentException("City cannot be null");
        }
        return cityDao.create(city);
    }

    public void deleteCity(City city) {
        if (city == null) {
            throw new IllegalArgumentException("Invalid city ID");
        }
        cityDao.delete(city);
    }

    public City getCityById(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID should be positive values");
        }
        return cityDao.getById(id);
    }

    public List<City> getAllCity() {
        return cityDao.getAll();
    }


}
