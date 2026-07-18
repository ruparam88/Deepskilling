package com.cognizant.exercise.week2.springdatajpa.ormlearn.service;

import com.cognizant.exercise.week2.springdatajpa.ormlearn.model.Attempt;
import com.cognizant.exercise.week2.springdatajpa.ormlearn.repository.AttemptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "ormlearnAttemptService")
public class AttemptService {

    @Autowired
    private AttemptRepository attemptRepository;

    @Transactional(readOnly = true)
    public Attempt getAttempt(int userId, int attemptId) {
        return attemptRepository.getAttemptDetails(userId, attemptId);
    }
}
