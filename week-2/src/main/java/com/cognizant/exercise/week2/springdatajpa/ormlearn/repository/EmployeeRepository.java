package com.cognizant.exercise.week2.springdatajpa.ormlearn.repository;

import com.cognizant.exercise.week2.springdatajpa.ormlearn.model.OrmEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "ormlearnEmployeeRepository")
public interface EmployeeRepository extends JpaRepository<OrmEmployee, Integer> {

    @Query("SELECT DISTINCT e FROM OrmEmployee e LEFT JOIN FETCH e.department LEFT JOIN FETCH e.skillList WHERE e.permanent = true")
    List<OrmEmployee> getAllPermanentEmployees();

    @Query("SELECT AVG(e.salary) FROM OrmEmployee e WHERE e.department.id = :deptId")
    Double getAverageSalary(@Param("deptId") Integer deptId);

    @Query(value = "SELECT * FROM employee", nativeQuery = true)
    List<OrmEmployee> getAllEmployeesNative();
}
