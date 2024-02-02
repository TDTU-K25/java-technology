package com.example.lab07;

import com.example.lab07.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;

@SpringBootApplication
public class Lab07Application {
    @Autowired
    private StudentRepository studentRepository;

    public static void main(String[] args) {
        SpringApplication.run(Lab07Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                System.out.println("Java Technology");

                studentRepository.findAllByOrderByAgeDescIeltsScoreAsc().forEach(System.out::println);
                System.out.println("---------------------------------------------------");
                studentRepository.findAllByOrderByAgeDescIeltsScoreAsc(PageRequest.of(1, 3)).forEach(System.out::println);
                System.out.println("---------------------------------------------------");
                studentRepository.findAll(PageRequest.of(1, 3)).forEach(System.out::println);
            }
        };
    }

}
