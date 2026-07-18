package com.cognizant.exercise.week2.springdatajpa.ems.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departments")
@org.hibernate.annotations.DynamicInsert
@org.hibernate.annotations.DynamicUpdate
public class EmsDepartment extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<EmsEmployee> employees = new ArrayList<>();

    public EmsDepartment() {}

    public EmsDepartment(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EmsEmployee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmsEmployee> employees) {
        this.employees = employees;
    }
}
