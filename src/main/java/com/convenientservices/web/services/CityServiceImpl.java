package com.convenientservices.web.services;

import com.convenientservices.web.entities.City;
import com.convenientservices.web.repositories.CityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CityServiceImpl implements CityService {
    private CityRepository repository;

    @Override
    public City findById (Long id) throws Exception {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public List<City> findAll () {
        return repository.findAll();
    }

    @Override
    public City save (City city) {
        return repository.save(city);
    }

    @Override
    public City findByName (String name) {
        return repository.findByName(name).orElseThrow();
    }

    @Override
    public String findCorrectNameOfCity (String name) {
        if (name != null) {
            List<City> cities = repository.findAll().stream()
                    .filter(city -> city.getName().toLowerCase(Locale.ROOT).equals(name.toLowerCase(Locale.ROOT)))
                    .collect(Collectors.toList());
            if (cities != null && !cities.isEmpty()) {
                return cities.get(0).getName();
            } else {
                return null;
            }
        }
        return name;
    }

}
