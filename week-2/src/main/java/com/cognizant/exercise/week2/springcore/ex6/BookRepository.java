package com.cognizant.exercise.week2.springcore.ex6;

import org.springframework.stereotype.Repository;

@Repository("ex6BookRepository")
public class BookRepository {
    public void execute() {
        System.out.println("[Ex 6] BookRepository: Accessing annotated repository.");
    }
}
