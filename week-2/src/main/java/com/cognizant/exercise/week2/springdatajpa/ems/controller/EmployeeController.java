package com.cognizant.exercise.week2.springdatajpa.ems.controller;

import com.cognizant.exercise.week2.springdatajpa.ems.dto.EmployeeDto;
import com.cognizant.exercise.week2.springdatajpa.ems.model.EmsDepartment;
import com.cognizant.exercise.week2.springdatajpa.ems.model.EmsEmployee;
import com.cognizant.exercise.week2.springdatajpa.ems.projection.EmployeeProjection;
import com.cognizant.exercise.week2.springdatajpa.ems.repository.DepartmentRepository;
import com.cognizant.exercise.week2.springdatajpa.ems.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ems/employees")
public class EmployeeController {

    @Autowired
    @Qualifier("emsEmployeeRepository")
    private EmployeeRepository employeeRepository;

    @Autowired
    @Qualifier("emsDepartmentRepository")
    private DepartmentRepository departmentRepository;

    // Get all employees
    @GetMapping
    public List<EmsEmployee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Get employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<EmsEmployee> getEmployeeById(@PathVariable Long id) {
        Optional<EmsEmployee> employee = employeeRepository.findById(id);
        return employee.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create employee
    @PostMapping
    public ResponseEntity<EmsEmployee> createEmployee(@RequestParam String name, 
                                                   @RequestParam String email, 
                                                   @RequestParam Long departmentId) {
        Optional<EmsDepartment> deptOpt = departmentRepository.findById(departmentId);
        if (deptOpt.isPresent()) {
            EmsEmployee employee = new EmsEmployee(name, email, deptOpt.get());
            EmsEmployee saved = employeeRepository.save(employee);
            return ResponseEntity.ok(saved);
        }
        return ResponseEntity.badRequest().build();
    }

    // Update employee
    @PutMapping("/{id}")
    public ResponseEntity<EmsEmployee> updateEmployee(@PathVariable Long id, 
                                                   @RequestParam String name, 
                                                   @RequestParam String email) {
        Optional<EmsEmployee> empOpt = employeeRepository.findById(id);
        if (empOpt.isPresent()) {
            EmsEmployee employee = empOpt.get();
            employee.setName(name);
            employee.setEmail(email);
            EmsEmployee updated = employeeRepository.save(employee);
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    // Delete employee
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Search employees
    @GetMapping("/search")
    public List<EmsEmployee> searchEmployees(@RequestParam String keyword) {
        return employeeRepository.searchEmployees(keyword);
    }

    // Paginated list of employees by department
    @GetMapping("/dept/{deptId}")
    public Page<EmsEmployee> getEmployeesByDepartment(@PathVariable Long deptId,
                                                   @RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "10") int size,
                                                   @RequestParam(defaultValue = "name,asc") String sort) {
        String[] sortParams = sort.split(",");
        Sort sortObj = Sort.by(Sort.Direction.fromString(sortParams[1]), sortParams[0]);
        Pageable pageable = PageRequest.of(page, size, sortObj);
        return employeeRepository.findByDepartmentId(deptId, pageable);
    }

    // Projections list
    @GetMapping("/projections/{deptId}")
    public List<EmployeeProjection> getProjectionsByDept(@PathVariable Long deptId) {
        return employeeRepository.findProjectionsByDepartmentId(deptId);
    }

    // DTO list
    @GetMapping("/dtos/{deptId}")
    public List<EmployeeDto> getDtosByDept(@PathVariable Long deptId) {
        return employeeRepository.findDtosByDepartmentId(deptId);
    }
}
