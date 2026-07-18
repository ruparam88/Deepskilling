package com.cognizant.exercise.week2;

import com.cognizant.exercise.week2.springdatajpa.ormlearn.model.*;
import com.cognizant.exercise.week2.springdatajpa.ormlearn.service.*;
import com.cognizant.exercise.week2.springdatajpa.ormlearn.service.exception.CountryNotFoundException;
import com.cognizant.exercise.week2.springdatajpa.ems.dto.EmployeeDto;
import com.cognizant.exercise.week2.springdatajpa.ems.projection.EmployeeProjection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

@SpringBootApplication
public class Week2Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Week2Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Week2Application.class, args);
        LOGGER.info("Spring Boot Application started successfully.");
    }

    @Bean
    public CommandLineRunner runAllExercises(
            CountryService countryService, 
            StockService stockService, 
            EmployeeService employeeService,
            AttemptService attemptService,
            @org.springframework.beans.factory.annotation.Qualifier("emsEmployeeRepository") 
            com.cognizant.exercise.week2.springdatajpa.ems.repository.EmployeeRepository emsEmployeeRepository
    ) {
        return (args) -> {
            LOGGER.info("==========================================");
            LOGGER.info("   STARTING ALL WEEK-2 EXERCISE TESTS     ");
            LOGGER.info("==========================================");

            // ----------------------------------------------------
            // SPRING CORE EXERCISES (EX 1 - 8)
            // ----------------------------------------------------
            runSpringCoreExercises();

            // ----------------------------------------------------
            // SPRING DATA JPA EXERCISES (ORM-LEARN HANDS-ON 1-9)
            // ----------------------------------------------------
            runSpringDataJpaOrmLearn(countryService, stockService, employeeService, attemptService);

            // ----------------------------------------------------
            // SPRING DATA JPA EXERCISES (EMPLOYEE MANAGEMENT SYSTEM)
            // ----------------------------------------------------
            runSpringDataJpaEMS(emsEmployeeRepository);

            LOGGER.info("==========================================");
            LOGGER.info("   ALL WEEK-2 EXERCISE TESTS COMPLETED    ");
            LOGGER.info("==========================================");
        };
    }

    private void runSpringCoreExercises() {
        LOGGER.info("--- [Spring Core] Running Exercises 1-8 ---");

        // Exercise 1: Configuring a Basic Spring Application
        LOGGER.info("[Ex 1] Loading XML application context...");
        ApplicationContext ctx1 = new ClassPathXmlApplicationContext("applicationContext-ex1.xml");
        com.cognizant.exercise.week2.springcore.ex1.BookService svc1 = ctx1.getBean(com.cognizant.exercise.week2.springcore.ex1.BookService.class);
        svc1.service();

        // Exercise 2: Implementing Dependency Injection
        LOGGER.info("[Ex 2] Loading DI context...");
        ApplicationContext ctx2 = new ClassPathXmlApplicationContext("applicationContext-ex2.xml");
        com.cognizant.exercise.week2.springcore.ex2.BookService svc2 = ctx2.getBean(com.cognizant.exercise.week2.springcore.ex2.BookService.class);
        svc2.service();

        // Exercise 3: Implementing Logging with Spring AOP
        LOGGER.info("[Ex 3] Loading AOP Logging context...");
        ApplicationContext ctx3 = new ClassPathXmlApplicationContext("applicationContext-ex3.xml");
        com.cognizant.exercise.week2.springcore.ex3.BookService svc3 = ctx3.getBean(com.cognizant.exercise.week2.springcore.ex3.BookService.class);
        svc3.service();

        // Exercise 5: Configuring the Spring IoC Container
        LOGGER.info("[Ex 5] Loading IoC Container context...");
        ApplicationContext ctx5 = new ClassPathXmlApplicationContext("applicationContext-ex5.xml");
        com.cognizant.exercise.week2.springcore.ex5.BookService svc5 = ctx5.getBean(com.cognizant.exercise.week2.springcore.ex5.BookService.class);
        svc5.service();

        // Exercise 6: Configuring Beans with Annotations
        LOGGER.info("[Ex 6] Loading component-scan annotated context...");
        ApplicationContext ctx6 = new ClassPathXmlApplicationContext("applicationContext-ex6.xml");
        com.cognizant.exercise.week2.springcore.ex6.BookService svc6 = ctx6.getBean(com.cognizant.exercise.week2.springcore.ex6.BookService.class);
        svc6.service();

        // Exercise 7: Implementing Constructor and Setter Injection
        LOGGER.info("[Ex 7] Loading Constructor & Setter Injection context...");
        ApplicationContext ctx7 = new ClassPathXmlApplicationContext("applicationContext-ex7.xml");
        com.cognizant.exercise.week2.springcore.ex7.BookService svc7 = ctx7.getBean(com.cognizant.exercise.week2.springcore.ex7.BookService.class);
        svc7.service();

        // Exercise 8: Implementing Basic AOP with Spring
        LOGGER.info("[Ex 8] Loading AOP (@Before and @After) context...");
        ApplicationContext ctx8 = new ClassPathXmlApplicationContext("applicationContext-ex8.xml");
        com.cognizant.exercise.week2.springcore.ex8.BookService svc8 = ctx8.getBean(com.cognizant.exercise.week2.springcore.ex8.BookService.class);
        svc8.service();

        LOGGER.info("--- [Spring Core] Exercises 1-8 Completed ---");
    }

    private void runSpringDataJpaOrmLearn(
            CountryService countryService, 
            StockService stockService, 
            EmployeeService employeeService,
            AttemptService attemptService
    ) {
        LOGGER.info("--- [Spring Data JPA - orm-learn] Starting Hands-on Tests ---");

        // Hands-on 1 & 5: getAllCountries
        LOGGER.info("[orm-learn] Retrieving all countries...");
        List<Country> countries = countryService.getAllCountries();
        LOGGER.debug("Countries found: {}", countries.size());

        // Hands-on 6: findCountryByCode
        try {
            Country country = countryService.findCountryByCode("IN");
            LOGGER.debug("Country search for IN: {}", country);
        } catch (CountryNotFoundException e) {
            LOGGER.error("Country IN search failed: {}", e.getMessage());
        }

        // Hands-on 7: addCountry
        Country c = new Country("ZZ", "Zootopia");
        countryService.addCountry(c);
        try {
            Country found = countryService.findCountryByCode("ZZ");
            LOGGER.debug("Added Country: {}", found);
        } catch (CountryNotFoundException e) {
            LOGGER.error("Country ZZ not added properly: {}", e.getMessage());
        }

        // Hands-on 8: updateCountry
        countryService.updateCountry("ZZ", "Zootopia Updated");
        try {
            Country found = countryService.findCountryByCode("ZZ");
            LOGGER.debug("Updated Country: {}", found);
        } catch (CountryNotFoundException e) {
            LOGGER.error("Country ZZ not updated: {}", e.getMessage());
        }

        // Hands-on 9: deleteCountry
        countryService.deleteCountry("ZZ");
        try {
            countryService.findCountryByCode("ZZ");
            LOGGER.error("Country ZZ not deleted!");
        } catch (CountryNotFoundException e) {
            LOGGER.debug("Country ZZ successfully deleted (Expected Exception): {}", e.getMessage());
        }

        // Part 2 Hands-on 1: Country partial matches sorted and starting with Z
        List<Country> ouCountries = countryService.searchCountrySorted("ou");
        LOGGER.debug("Countries containing 'ou' sorted: {}", ouCountries);
        List<Country> zCountries = countryService.findCountriesStartingWith("Z");
        LOGGER.debug("Countries starting with 'Z': {}", zCountries);

        // Part 2 Hands-on 2: Stock queries
        List<Stock> fbSep = stockService.getFacebookStockInSeptember2019();
        LOGGER.debug("Facebook Sep 2019 stocks: {}", fbSep);
        List<Stock> googlHigh = stockService.getGoogleStockGreaterThan1250();
        LOGGER.debug("Google > 1250 stocks: {}", googlHigh);
        List<Stock> top3 = stockService.getTop3HighestVolumeStocks();
        LOGGER.debug("Top 3 volume stocks: {}", top3);

        // Part 3 Hands-on 2: Get all permanent employees using HQL join fetch
        List<OrmEmployee> employees = employeeService.getAllPermanentEmployees();
        LOGGER.debug("Permanent Employees (Fetched via HQL join fetch):");
        for (OrmEmployee emp : employees) {
            LOGGER.debug("  Employee: {}, Dept: {}, Skills: {}", emp.getName(), emp.getDepartment().getName(), emp.getSkillList());
        }

        // Part 3 Hands-on 3: Fetch quiz attempt details
        Attempt attempt = attemptService.getAttempt(1, 1);
        if (attempt != null) {
            LOGGER.debug("Quiz Attempt by User: {}, Attempt ID: {}, Date: {}", attempt.getUser().getName(), attempt.getId(), attempt.getDate());
            for (AttemptQuestion aq : attempt.getAttemptQuestions()) {
                Question q = aq.getQuestion();
                LOGGER.debug("  Question: {}", q.getText());
                for (Options o : q.getOptions()) {
                    boolean isSelected = aq.getAttemptOption() != null && aq.getAttemptOption().getOption().getId().equals(o.getId());
                    LOGGER.debug("    {}) {} \t Score: {} \t Selected: {}", o.getId(), o.getText(), o.getScore(), isSelected);
                }
            }
        }

        // Part 3 Hands-on 4: Get average salary using HQL
        Double avgSalary = employeeService.getAverageSalary(2);
        LOGGER.debug("Average Salary for Dept 2: {}", avgSalary);

        // Part 3 Hands-on 5: Native Query
        List<OrmEmployee> allEmps = employeeService.getAllEmployeesNative();
        LOGGER.debug("All employees native query count: {}", allEmps.size());

        // Part 3 Hands-on 6: Criteria Query
        List<OrmEmployee> filtered = employeeService.searchEmployeesByCriteria("John", 60000.0, 90000.0, 2);
        LOGGER.debug("Dynamic criteria search results: {}", filtered);

        LOGGER.info("--- [Spring Data JPA - orm-learn] Hands-on Tests Completed ---");
    }

    private void runSpringDataJpaEMS(
            com.cognizant.exercise.week2.springdatajpa.ems.repository.EmployeeRepository emsEmployeeRepository
    ) {
        LOGGER.info("--- [Spring Data JPA - EMS] Starting Exercise Tests ---");

        // Ex 4: CRUD operations test
        List<com.cognizant.exercise.week2.springdatajpa.ems.model.EmsEmployee> emps = emsEmployeeRepository.findAll();
        LOGGER.debug("All EMS Employees: {}", emps);

        // Ex 5: Named Query test
        com.cognizant.exercise.week2.springdatajpa.ems.model.EmsEmployee namedEmp = emsEmployeeRepository.findByEmailNamed("alice@ems.com");
        LOGGER.debug("Found by named query: {}", namedEmp != null ? namedEmp.getName() : "null");

        // Ex 8: Projections test
        List<EmployeeProjection> projections = emsEmployeeRepository.findProjectionsByDepartmentId(2L);
        LOGGER.debug("Interface projections count for Dept 2: {}", projections.size());
        for (EmployeeProjection ep : projections) {
            LOGGER.debug("  Projection - ID: {}, Name: {}, Email: {}", ep.getId(), ep.getName(), ep.getEmail());
        }

        List<EmployeeDto> dtos = emsEmployeeRepository.findDtosByDepartmentId(2L);
        LOGGER.debug("Class DTO projections count for Dept 2: {}", dtos.size());
        for (EmployeeDto dto : dtos) {
            LOGGER.debug("  DTO - Name: {}, Email: {}", dto.getName(), dto.getEmail());
        }

        LOGGER.info("--- [Spring Data JPA - EMS] Exercise Tests Completed ---");
    }
}
