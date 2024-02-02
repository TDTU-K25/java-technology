package com.example.lab07.repository;

import com.example.lab07.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {
    List<Student> findAllByAgeGreaterThanEqual(Integer age);
    int countAllByIeltsScore(Double ieltsScore);
    List<Student> findAllByNameContainingIgnoreCase(String word);

}
