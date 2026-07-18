package com.cognizant.springlearn.controller;

import com.cognizant.springlearn.Employee;
import com.cognizant.springlearn.service.EmployeeService;
import com.cognizant.springlearn.service.exception.EmployeeNotFoundException;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class EmployeeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public ArrayList<Employee> getAllEmployees() {
        LOGGER.info("START - getAllEmployees");
        ArrayList<Employee> employees = employeeService.getAllEmployees();
        LOGGER.debug("Returning employees: {}", employees);
        LOGGER.info("END - getAllEmployees");
        return employees;
    }

    @PutMapping("/employees")
    public void updateEmployee(@RequestBody @Valid Employee employee) throws EmployeeNotFoundException {
        LOGGER.info("START - updateEmployee: {}", employee);
        employeeService.updateEmployee(employee);
        LOGGER.info("END - updateEmployee");
    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable Long id) throws EmployeeNotFoundException {
        LOGGER.info("START - deleteEmployee id: {}", id);
        employeeService.deleteEmployee(id);
        LOGGER.info("END - deleteEmployee");
    }
}
