package com.cognizant.exercise.week2.springcore.ex7;

public class BookService {
    private BookRepository bookRepository;
    private String serviceName;

    // Constructor Injection
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Setter Injection
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void service() {
        System.out.println("[Ex 7] BookService (" + serviceName + "): Running service.");
        if (bookRepository != null) {
            bookRepository.execute();
        }
    }
}
