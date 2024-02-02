package com.example.springcommerce.repository;

import com.example.springcommerce.models.Order;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("SELECT o FROM Order o ORDER BY o.id DESC Limit 1")
    Order getLatestOrder();

    @Transactional
    @Modifying
    @Query("DELETE FROM Order o WHERE o.id = :id")
    void deleteByIdOfMy(@Param("id") int id);
}
