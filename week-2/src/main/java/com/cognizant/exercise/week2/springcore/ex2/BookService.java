package com.cognizant.exercise.week2.springcore.ex2;

public class BookService {
    private BookRepository bookRepository;

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void service() {
        System.out.println("[Ex 2] BookService: Executing service with dependency injection.");
        if (bookRepository != null) {
            bookRepository.execute();
        }
    }
}
