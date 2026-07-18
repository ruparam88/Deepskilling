package com.cognizant.exercise.week2.springdatajpa.ormlearn.service;

import com.cognizant.exercise.week2.springdatajpa.ormlearn.model.OrmEmployee;
import com.cognizant.exercise.week2.springdatajpa.ormlearn.repository.EmployeeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(value = "ormlearnEmployeeService")
public class EmployeeService {

    @Autowired
    @Qualifier("ormlearnEmployeeRepository")
    private EmployeeRepository employeeRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public List<OrmEmployee> getAllPermanentEmployees() {
        return employeeRepository.getAllPermanentEmployees();
    }

    @Transactional(readOnly = true)
    public Double getAverageSalary(Integer deptId) {
        return employeeRepository.getAverageSalary(deptId);
    }

    @Transactional(readOnly = true)
    public List<OrmEmployee> getAllEmployeesNative() {
        return employeeRepository.getAllEmployeesNative();
    }

    // Criteria Query (Hands-on 6)
    @Transactional(readOnly = true)
    public List<OrmEmployee> searchEmployeesByCriteria(String nameKeyword, Double minSalary, Double maxSalary, Integer deptId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<OrmEmployee> query = cb.createQuery(OrmEmployee.class);
        Root<OrmEmployee> employee = query.from(OrmEmployee.class);
        
        List<Predicate> predicates = new ArrayList<>();
        
        if (nameKeyword != null && !nameKeyword.trim().isEmpty()) {
            predicates.add(cb.like(employee.get("name"), "%" + nameKeyword + "%"));
        }
        
        if (minSalary != null) {
            predicates.add(cb.ge(employee.get("salary"), minSalary));
        }
        
        if (maxSalary != null) {
            predicates.add(cb.le(employee.get("salary"), maxSalary));
        }
        
        if (deptId != null) {
            predicates.add(cb.equal(employee.get("department").get("id"), deptId));
        }
        
        query.select(employee).where(cb.and(predicates.toArray(new Predicate[0])));
        
        return entityManager.createQuery(query).getResultList();
    }
}
