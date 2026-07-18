package com.cognizant.exercise.week2.springdatajpa.ems.repository;

import com.cognizant.exercise.week2.springdatajpa.ems.dto.EmployeeDto;
import com.cognizant.exercise.week2.springdatajpa.ems.model.EmsEmployee;
import com.cognizant.exercise.week2.springdatajpa.ems.projection.EmployeeProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "emsEmployeeRepository")
public interface EmployeeRepository extends JpaRepository<EmsEmployee, Long> {

    List<EmsEmployee> findByName(String name);
    List<EmsEmployee> findByEmail(String email);
    List<EmsEmployee> findByDepartmentName(String deptName);
    List<EmsEmployee> findByNameContaining(String infix);

    @Query("SELECT e FROM EmsEmployee e WHERE e.name LIKE %:keyword% OR e.email LIKE %:keyword%")
    List<EmsEmployee> searchEmployees(@Param("keyword") String keyword);

    EmsEmployee findByEmailNamed(@Param("email") String email);
    EmsEmployee findByNameNamed(@Param("name") String name);

    Page<EmsEmployee> findByDepartmentId(Long deptId, Pageable pageable);

    List<EmployeeProjection> findProjectionsByDepartmentId(Long deptId);

    @Query("SELECT new com.cognizant.exercise.week2.springdatajpa.ems.dto.EmployeeDto(e.name, e.email) FROM EmsEmployee e WHERE e.department.id = :deptId")
    List<EmployeeDto> findDtosByDepartmentId(@Param("deptId") Long deptId);
}
