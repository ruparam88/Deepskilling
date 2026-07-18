package com.cognizant.springlearn.dao;

import com.cognizant.springlearn.Employee;
import com.cognizant.springlearn.service.exception.EmployeeNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class EmployeeDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDao.class);

    private static ArrayList<Employee> EMPLOYEE_LIST = new ArrayList<>();

    @SuppressWarnings("unchecked")
    public EmployeeDao() {
        LOGGER.info("START - EmployeeDao Constructor");
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("employee.xml");
            EMPLOYEE_LIST = (ArrayList<Employee>) context.getBean("employeeList", ArrayList.class);
            LOGGER.debug("Loaded employees count: {}", EMPLOYEE_LIST.size());
        } catch (Exception e) {
            LOGGER.error("Failed to load employeeList from XML: ", e);
        }
        LOGGER.info("END - EmployeeDao Constructor");
    }

    public ArrayList<Employee> getAllEmployees() {
        LOGGER.info("START - getAllEmployees");
        LOGGER.info("END - getAllEmployees");
        return EMPLOYEE_LIST;
    }

    public void updateEmployee(Employee employee) throws EmployeeNotFoundException {
        LOGGER.info("START - updateEmployee: {}", employee);
        boolean found = false;
        for (int i = 0; i < EMPLOYEE_LIST.size(); i++) {
            if (EMPLOYEE_LIST.get(i).getId().equals(employee.getId())) {
                EMPLOYEE_LIST.set(i, employee);
                found = true;
                break;
            }
        }
        if (!found) {
            LOGGER.error("Employee not found with id: {}", employee.getId());
            throw new EmployeeNotFoundException("Employee not found with ID: " + employee.getId());
        }
        LOGGER.info("END - updateEmployee");
    }

    public void deleteEmployee(Long id) throws EmployeeNotFoundException {
        LOGGER.info("START - deleteEmployee id: {}", id);
        boolean found = false;
        for (int i = 0; i < EMPLOYEE_LIST.size(); i++) {
            if (EMPLOYEE_LIST.get(i).getId().equals(id)) {
                EMPLOYEE_LIST.remove(i);
                found = true;
                break;
            }
        }
        if (!found) {
            LOGGER.error("Employee not found for deletion with id: {}", id);
            throw new EmployeeNotFoundException("Employee not found with ID: " + id);
        }
        LOGGER.info("END - deleteEmployee");
    }
}
