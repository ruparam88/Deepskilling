package com.cognizant.exercise.week2.springdatajpa.ems.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "employees")
@org.hibernate.annotations.DynamicInsert
@org.hibernate.annotations.DynamicUpdate
@NamedQueries({
    @NamedQuery(name = "EmsEmployee.findByEmailNamed", query = "SELECT e FROM EmsEmployee e WHERE e.email = :email"),
    @NamedQuery(name = "EmsEmployee.findByNameNamed", query = "SELECT e FROM EmsEmployee e WHERE e.name = :name")
})
public class EmsEmployee extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    @JsonBackReference
    private EmsDepartment department;

    public EmsEmployee() {}

    public EmsEmployee(String name, String email, EmsDepartment department) {
        this.name = name;
        this.email = email;
        this.department = department;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EmsDepartment getDepartment() {
        return department;
    }

    public void setDepartment(EmsDepartment department) {
        this.department = department;
    }
}
