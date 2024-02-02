package com.example.springcommerce.repository;

import com.example.springcommerce.models.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends JpaRepository<Color, Integer> {
    Color findColorByName(String name);
}
