package com.cognizant.exercise.week2.springcore.ex5;

public class BookService {
    private BookRepository bookRepository;

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void service() {
        System.out.println("[Ex 5] BookService: Executing library logic under central bean management.");
        if (bookRepository != null) {
            bookRepository.execute();
        }
    }
}
