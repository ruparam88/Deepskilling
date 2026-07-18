package com.cognizant.exercise.week2.springdatajpa.ormlearn.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "department")
public class OrmDepartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dp_id")
    private Integer id;

    @Column(name = "dp_name", length = 45, nullable = false)
    private String name;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private List<OrmEmployee> employeeList;

    public OrmDepartment() {}

    public OrmDepartment(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<OrmEmployee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<OrmEmployee> employeeList) {
        this.employeeList = employeeList;
    }

    @Override
    public String toString() {
        return "OrmDepartment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
