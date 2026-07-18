package com.cognizant.exercise.week2.springcore.ex6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    @org.springframework.beans.factory.annotation.Qualifier("ex6BookRepository")
    private BookRepository bookRepository;

    public void service() {
        System.out.println("[Ex 6] BookService: Executing annotated service.");
        if (bookRepository != null) {
            bookRepository.execute();
        }
    }
}
