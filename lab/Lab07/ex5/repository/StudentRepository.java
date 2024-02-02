package com.example.lab07.repository;

import com.example.lab07.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {
    @Query("FROM Student WHERE age >= :age")
    List<Student> findByAgeGreaterEqual(@Param("age") Integer age);

    @Query("SELECT COUNT(*) FROM Student WHERE ieltsScore = :ieltsScore")
    int countByIeltsScore(@Param("ieltsScore") Double ieltsScore);

    @Query("FROM Student WHERE name LIKE %:word%")
    List<Student> findByNameContainIgnoreCase(@Param("word") String word);

}
