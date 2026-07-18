package com.cognizant.springlearn.service;

import com.cognizant.springlearn.Country;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CountryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CountryService.class);

    private ArrayList<Country> countryList = new ArrayList<>();

    @SuppressWarnings("unchecked")
    public CountryService() {
        LOGGER.info("START - CountryService Constructor");
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
            countryList = (ArrayList<Country>) context.getBean("countryList", ArrayList.class);
            LOGGER.debug("Loaded countries: {}", countryList);
        } catch (Exception e) {
            LOGGER.error("Failed to load countryList from XML: ", e);
        }
        LOGGER.info("END - CountryService Constructor");
    }

    public ArrayList<Country> getAllCountries() {
        LOGGER.info("START - getAllCountries");
        LOGGER.info("END - getAllCountries");
        return countryList;
    }

    public Country getCountry(String code) throws CountryNotFoundException {
        LOGGER.info("START - getCountry code: {}", code);
        Country match = countryList.stream()
                .filter(c -> c.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElse(null);

        if (match == null) {
            LOGGER.error("Country not found: {}", code);
            throw new CountryNotFoundException("Country not found: " + code);
        }
        LOGGER.info("END - getCountry");
        return match;
    }

    public void addCountry(Country country) {
        LOGGER.info("START - addCountry: {}", country);
        countryList.add(country);
        LOGGER.info("END - addCountry");
    }
}
