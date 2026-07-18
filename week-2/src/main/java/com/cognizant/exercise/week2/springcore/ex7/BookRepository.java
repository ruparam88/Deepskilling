package com.cognizant.exercise.week2.springcore.ex7;

public class BookRepository {
    private String name;

    public BookRepository(String name) {
        this.name = name;
    }

    public void execute() {
        System.out.println("[Ex 7] BookRepository (" + name + "): Retrieving books.");
    }
}
