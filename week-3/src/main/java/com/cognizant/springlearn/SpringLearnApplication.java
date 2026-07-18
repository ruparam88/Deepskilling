package com.cognizant.springlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@SpringBootApplication
public class SpringLearnApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringLearnApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringLearnApplication.class, args);
        LOGGER.info("Spring Boot Application started successfully.");
    }

    @Bean
    public CommandLineRunner runSpringCoreDemos() {
        return (args) -> {
            LOGGER.info("==========================================");
            LOGGER.info("   STARTING SPRING CORE XML LOADING DEMOS ");
            LOGGER.info("==========================================");

            displayDate();
            displayCountry();
            displayCountries();

            LOGGER.info("==========================================");
            LOGGER.info("   SPRING CORE XML LOADING DEMOS COMPLETE ");
            LOGGER.info("==========================================");
        };
    }

    public void displayDate() {
        LOGGER.info("START - displayDate");
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("date-format.xml");
            SimpleDateFormat format = context.getBean("dateFormat", SimpleDateFormat.class);
            Date parsedDate = format.parse("31/12/2018");
            LOGGER.debug("Parsed Date: {}", parsedDate);
        } catch (Exception e) {
            LOGGER.error("Failed to parse date: ", e);
        }
        LOGGER.info("END - displayDate");
    }

    public void displayCountry() {
        LOGGER.info("START - displayCountry");
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        
        LOGGER.info("Requesting country bean 1st time...");
        Country country = context.getBean("country", Country.class);
        
        LOGGER.info("Requesting country bean 2nd time...");
        Country anotherCountry = context.getBean("country", Country.class);
        
        LOGGER.debug("Country 1: {}", country);
        LOGGER.debug("Country 2: {}", anotherCountry);
        LOGGER.info("END - displayCountry");
    }

    @SuppressWarnings("unchecked")
    public void displayCountries() {
        LOGGER.info("START - displayCountries");
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        ArrayList<Country> countries = (ArrayList<Country>) context.getBean("countryList", ArrayList.class);
        LOGGER.debug("Country List: {}", countries);
        LOGGER.info("END - displayCountries");
    }
}
