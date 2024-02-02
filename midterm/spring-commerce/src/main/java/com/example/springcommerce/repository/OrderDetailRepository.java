package com.example.springcommerce.repository;

import com.example.springcommerce.models.OrderDetail;
import com.example.springcommerce.models.OrderDetailKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailKey> {
}
