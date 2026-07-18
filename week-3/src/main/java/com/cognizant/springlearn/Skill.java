package com.cognizant.springlearn;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Skill {

    @NotNull(message = "Skill ID cannot be null")
    private Long id;

    @NotNull(message = "Skill name cannot be null")
    @NotBlank(message = "Skill name cannot be blank")
    @Size(min = 1, max = 30, message = "Skill name length must be between 1 and 30 characters")
    private String name;

    public Skill() {}

    public Skill(Long id, String name) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
