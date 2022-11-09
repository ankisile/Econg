package com.example.bunjang.repository;

import com.example.bunjang.entity.Orders;
import com.example.bunjang.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    List<Orders> findByUser_Id(Long userId);

    @Query("SELECT p FROM Orders o LEFT JOIN User u ON o.user = u LEFT JOIN Project p ON o.project = p where u.id = :userId")
    List<Project> findProjectsByUserId(Long userId);
}
