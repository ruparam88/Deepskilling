package com.cognizant.exercise.week2.springdatajpa.ormlearn.repository;

import com.cognizant.exercise.week2.springdatajpa.ormlearn.model.OrmDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "ormlearnDepartmentRepository")
public interface DepartmentRepository extends JpaRepository<OrmDepartment, Integer> {
}
