package com.cognizant.springlearn.controller;

import com.cognizant.springlearn.Country;
import com.cognizant.springlearn.service.CountryService;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class CountryController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);

    @Autowired
    private CountryService countryService;

    public CountryController() {
        LOGGER.debug("Inside CountryController Constructor.");
    }

    // GET /country - returns India country details (for Hands-on 2 REST Country Web Service & MockMvc test)
    @GetMapping("/country")
    public Country getCountryIndia() {
        LOGGER.info("START - getCountryIndia");
        try {
            Country country = countryService.getCountry("IN");
            LOGGER.debug("Returning default country: {}", country);
            LOGGER.info("END - getCountryIndia");
            return country;
        } catch (CountryNotFoundException e) {
            LOGGER.error("Default country IN not found: ", e);
            LOGGER.info("END - getCountryIndia");
            return null;
        }
    }

    // GET /countries - returns all countries
    @GetMapping("/countries")
    public ArrayList<Country> getAllCountries() {
        LOGGER.info("START - getAllCountries");
        ArrayList<Country> countries = countryService.getAllCountries();
        LOGGER.debug("Returning countries: {}", countries);
        LOGGER.info("END - getAllCountries");
        return countries;
    }

    // GET /countries/{code} - returns a specific country based on country code
    @GetMapping("/countries/{code}")
    public Country getCountry(@PathVariable String code) throws CountryNotFoundException {
        LOGGER.info("START - getCountry code: {}", code);
        Country country = countryService.getCountry(code);
        LOGGER.debug("Found country: {}", country);
        LOGGER.info("END - getCountry");
        return country;
    }

    // POST /countries - adds a country
    @PostMapping("/countries")
    public Country addCountry(@RequestBody @Valid Country country) {
        LOGGER.info("START - addCountry: {}", country);
        countryService.addCountry(country);
        LOGGER.info("END - addCountry");
        return country;
    }
}
