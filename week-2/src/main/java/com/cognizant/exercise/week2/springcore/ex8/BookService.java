package com.cognizant.exercise.week2.springcore.ex8;

public class BookService {
    private BookRepository bookRepository;

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void service() {
        System.out.println("[Ex 8] BookService: Performing library logic.");
        if (bookRepository != null) {
            bookRepository.execute();
        }
    }
}
