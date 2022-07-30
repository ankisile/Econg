package com.example.bunjang.repository;

import com.example.bunjang.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p, pi FROM Product p LEFT JOIN ProductImage pi ON pi.product = p where pi.represent = true")
    List<Object[]> getAllProductsWithImage();

    @Query("SELECT p, pi FROM Product p LEFT JOIN ProductImage pi ON pi.product = p where pi.represent = true and p.productType = 'CROWD'")
    List<Object[]> getCrowdProductsWithImage();

    @Query("SELECT p, pi FROM Product p LEFT JOIN ProductImage pi ON pi.product = p where pi.represent = true and p.productType = 'SELLPRODUCT'")
    List<Object[]> getProductsWithImage();

    @Query("SELECT p, pi FROM Product p LEFT JOIN ProductImage pi ON pi.product = p where pi.represent = true and p.user.userId = :id")
    List<Object[]> getCompanyProductsWithImage(@Param("id") Long id);


//    @Query("SELECT p, pi FROM Product p LEFT JOIN ProductImage pi ON pi.product = p where p.productId = :id")
//    List<Object[]> getProductDetail(@Param("id") Long id);
}
