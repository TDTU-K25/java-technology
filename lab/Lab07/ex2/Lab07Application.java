package com.example.lab07;

import com.example.lab07.model.Student;
import com.example.lab07.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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

                studentRepository.save(new Student(1, "Alex", 18, "alex@gmail.com", 6.5));
                studentRepository.save(new Student(2, "Mary", 25, "mary@gmail.com", 5.5));
                studentRepository.save(new Student(3, "Doe", 16, "doe@gmail.com", 7.0));

                System.out.println("------------------Read------------------");
                studentRepository.findAll().forEach(System.out::println);

                System.out.println("------------------Update------------------");
                Student updatedStudent = studentRepository.findById(3).get();
                updatedStudent.setName("Jack");
                updatedStudent.setAge(30);
                updatedStudent.setEmail("jack@gmail.com");
                studentRepository.save(updatedStudent);
                studentRepository.findAll().forEach(System.out::println);

                System.out.println("------------------Delete------------------");
                studentRepository.deleteById(2);
                studentRepository.findAll().forEach(System.out::println);
            }
        };
    }

}
