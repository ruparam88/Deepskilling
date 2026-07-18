package com.cognizant.exercise.week2.springdatajpa.ems.controller;

import com.cognizant.exercise.week2.springdatajpa.ems.model.EmsDepartment;
import com.cognizant.exercise.week2.springdatajpa.ems.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ems/departments")
public class DepartmentController {

    @Autowired
    @Qualifier("emsDepartmentRepository")
    private DepartmentRepository departmentRepository;

    // Get all departments
    @GetMapping
    public List<EmsDepartment> getAllDepartments() {
        return departmentRepository.findAll();
    }

    // Get department by ID
    @GetMapping("/{id}")
    public ResponseEntity<EmsDepartment> getDepartmentById(@PathVariable Long id) {
        Optional<EmsDepartment> department = departmentRepository.findById(id);
        return department.map(ResponseEntity::ok)
                         .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create department
    @PostMapping
    public EmsDepartment createDepartment(@RequestParam String name) {
        EmsDepartment department = new EmsDepartment(name);
        return departmentRepository.save(department);
    }

    // Delete department
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        if (departmentRepository.existsById(id)) {
            departmentRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
