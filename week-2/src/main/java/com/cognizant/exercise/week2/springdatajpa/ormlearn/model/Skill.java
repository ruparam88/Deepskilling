package com.cognizant.exercise.week2.springdatajpa.ormlearn.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "skill")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sk_id")
    private Integer id;

    @Column(name = "sk_name", length = 45, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "skillList", fetch = FetchType.LAZY)
    private List<OrmEmployee> employeeList;

    public Skill() {}

    public Skill(String name) {
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
        return "Skill{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
