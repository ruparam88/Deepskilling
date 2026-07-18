package com.cognizant.springlearn.service;

import com.cognizant.springlearn.Employee;
import com.cognizant.springlearn.dao.EmployeeDao;
import com.cognizant.springlearn.service.exception.EmployeeNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class EmployeeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeDao employeeDao;

    @Transactional(readOnly = true)
    public ArrayList<Employee> getAllEmployees() {
        LOGGER.info("START - getAllEmployees");
        LOGGER.info("END - getAllEmployees");
        return employeeDao.getAllEmployees();
    }

    @Transactional
    public void updateEmployee(Employee employee) throws EmployeeNotFoundException {
        LOGGER.info("START - updateEmployee: {}", employee);
        employeeDao.updateEmployee(employee);
        LOGGER.info("END - updateEmployee");
    }

    @Transactional
    public void deleteEmployee(Long id) throws EmployeeNotFoundException {
        LOGGER.info("START - deleteEmployee id: {}", id);
        employeeDao.deleteEmployee(id);
        LOGGER.info("END - deleteEmployee");
    }
}
