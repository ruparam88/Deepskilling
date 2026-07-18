package com.cognizant.springlearn.service;

import com.cognizant.springlearn.Department;
import com.cognizant.springlearn.dao.DepartmentDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DepartmentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentService.class);

    @Autowired
    private DepartmentDao departmentDao;

    public ArrayList<Department> getAllDepartments() {
        LOGGER.info("START - getAllDepartments");
        LOGGER.info("END - getAllDepartments");
        return departmentDao.getAllDepartments();
    }
}
