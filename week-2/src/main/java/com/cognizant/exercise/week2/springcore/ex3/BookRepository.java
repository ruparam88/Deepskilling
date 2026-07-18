package com.cognizant.exercise.week2.springcore.ex3;

public class BookRepository {
    public void execute() {
        System.out.println("[Ex 3] BookRepository: Accessing database of books.");
        try {
            Thread.sleep(80); // Simulate database delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
