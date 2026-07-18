package com.cognizant.exercise.week2.springcore.ex3;

public class BookService {
    private BookRepository bookRepository;

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void service() {
        System.out.println("[Ex 3] BookService: Serving book requests.");
        if (bookRepository != null) {
            bookRepository.execute();
        }
        try {
            Thread.sleep(120); // Simulate execution delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
