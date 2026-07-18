package com.cognizant.exercise.week2.springdatajpa.ormlearn.service;

import com.cognizant.exercise.week2.springdatajpa.ormlearn.model.Country;
import com.cognizant.exercise.week2.springdatajpa.ormlearn.repository.CountryRepository;
import com.cognizant.exercise.week2.springdatajpa.ormlearn.service.exception.CountryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service(value = "ormlearnCountryService")
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Transactional(readOnly = true)
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Country findCountryByCode(String countryCode) throws CountryNotFoundException {
        Optional<Country> result = countryRepository.findById(countryCode);
        if (!result.isPresent()) {
            throw new CountryNotFoundException("Country not found: " + countryCode);
        }
        return result.get();
    }

    @Transactional
    public void addCountry(Country country) {
        countryRepository.save(country);
    }

    @Transactional
    public void updateCountry(String code, String name) {
        Optional<Country> result = countryRepository.findById(code);
        if (result.isPresent()) {
            Country country = result.get();
            country.setName(name);
            countryRepository.save(country);
        }
    }

    @Transactional
    public void deleteCountry(String countryCode) {
        countryRepository.deleteById(countryCode);
    }

    @Transactional(readOnly = true)
    public List<Country> searchCountry(String keyword) {
        return countryRepository.findByNameContaining(keyword);
    }

    @Transactional(readOnly = true)
    public List<Country> searchCountrySorted(String keyword) {
        return countryRepository.findByNameContainingOrderByNameAsc(keyword);
    }

    @Transactional(readOnly = true)
    public List<Country> findCountriesStartingWith(String prefix) {
        return countryRepository.findByNameStartingWith(prefix);
    }
}
