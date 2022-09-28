package com.example.bunjang.repository;

import com.example.bunjang.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    List<Orders> findByUser_Id(Long userId);
}
