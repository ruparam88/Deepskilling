package com.cognizant.exercise.week2.springdatajpa.ormlearn.repository;

import com.cognizant.exercise.week2.springdatajpa.ormlearn.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Integer> {
}
