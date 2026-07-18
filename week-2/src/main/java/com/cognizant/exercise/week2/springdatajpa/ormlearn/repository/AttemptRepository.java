package com.cognizant.exercise.week2.springdatajpa.ormlearn.repository;

import com.cognizant.exercise.week2.springdatajpa.ormlearn.model.Attempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AttemptRepository extends JpaRepository<Attempt, Integer> {

    @Query("SELECT DISTINCT a FROM Attempt a " +
           "LEFT JOIN FETCH a.user u " +
           "LEFT JOIN FETCH a.attemptQuestions aq " +
           "LEFT JOIN FETCH aq.question q " +
           "LEFT JOIN FETCH q.options o " +
           "LEFT JOIN FETCH aq.attemptOption ao " +
           "LEFT JOIN FETCH ao.option " +
           "WHERE u.id = :userId AND a.id = :attemptId")
    Attempt getAttemptDetails(@Param("userId") Integer userId, @Param("attemptId") Integer attemptId);
}
