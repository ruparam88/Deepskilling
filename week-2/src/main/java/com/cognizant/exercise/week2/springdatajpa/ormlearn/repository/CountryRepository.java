package com.cognizant.exercise.week2.springdatajpa.ormlearn.repository;

import com.cognizant.exercise.week2.springdatajpa.ormlearn.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {
    List<Country> findByNameContaining(String infix);
    List<Country> findByNameContainingOrderByNameAsc(String infix);
    List<Country> findByNameStartingWith(String prefix);
}
