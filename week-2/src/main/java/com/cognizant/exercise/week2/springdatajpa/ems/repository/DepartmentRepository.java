package com.cognizant.exercise.week2.springdatajpa.ems.repository;

import com.cognizant.exercise.week2.springdatajpa.ems.model.EmsDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository(value = "emsDepartmentRepository")
public interface DepartmentRepository extends JpaRepository<EmsDepartment, Long> {
    Optional<EmsDepartment> findByName(String name);
}
