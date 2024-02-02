package com.example.springcommerce.repository;

import com.example.springcommerce.models.Brand;
import com.example.springcommerce.models.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p WHERE p.brand.name = COALESCE(:brandName, p.brand.name) AND p.category.name = COALESCE(:categoryName, p.category.name)  AND (:minPrice IS NULL OR p.price >= :minPrice) AND (:maxPrice IS NULL OR p.price <= :maxPrice) AND p.color.name = COALESCE(:colorName, p.color.name)")
    List<Product> filter(@Param("brandName") String brandName, @Param("categoryName") String categoryName, @Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice, @Param("colorName") String colorName);

    List<Product> findAllByBrand(Brand brand);

    @Transactional
    @Modifying
    @Query("DELETE FROM Product p WHERE p.id = :id")
    void deleteByIdOfMy(@Param("id") int id);
}
