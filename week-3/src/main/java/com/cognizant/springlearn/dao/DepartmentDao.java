package com.cognizant.springlearn.dao;

import com.cognizant.springlearn.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class DepartmentDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentDao.class);

    private static ArrayList<Department> DEPARTMENT_LIST = new ArrayList<>();

    @SuppressWarnings("unchecked")
    public DepartmentDao() {
        LOGGER.info("START - DepartmentDao Constructor");
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("employee.xml");
            DEPARTMENT_LIST = (ArrayList<Department>) context.getBean("departmentList", ArrayList.class);
            LOGGER.debug("Loaded departments count: {}", DEPARTMENT_LIST.size());
        } catch (Exception e) {
            LOGGER.error("Failed to load departmentList from XML: ", e);
        }
        LOGGER.info("END - DepartmentDao Constructor");
    }

    public ArrayList<Department> getAllDepartments() {
        LOGGER.info("START - getAllDepartments");
        LOGGER.info("END - getAllDepartments");
        return DEPARTMENT_LIST;
    }
}
