package com.example.lab07.repository;

import com.example.lab07.model.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends PagingAndSortingRepository<Student, Integer> {
    List<Student> findAllByOrderByAgeDescIeltsScoreAsc();
    List<Student> findAllByOrderByAgeDescIeltsScoreAsc(Pageable pageable);
}
