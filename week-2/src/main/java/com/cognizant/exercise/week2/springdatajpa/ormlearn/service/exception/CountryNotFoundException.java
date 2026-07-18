package com.cognizant.exercise.week2.springdatajpa.ormlearn.service.exception;

public class CountryNotFoundException extends Exception {
    public CountryNotFoundException(String message) {
        super(message);
    }
}
