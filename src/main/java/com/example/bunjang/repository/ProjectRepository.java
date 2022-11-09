package com.example.bunjang.repository;

import com.example.bunjang.entity.Project;
import com.example.bunjang.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {


    List<Project> findByStatusOrderByIdDesc(String status);

    @Query("select u from Project p LEFT JOIN User u ON p.user = u order by p.id desc ")
    List<User> findUserOrderByProjectIdDesc();

    List<Project> findByUser_Id(Long userId);
//    @Query("SELECT p, pi FROM Project p LEFT JOIN ProjectImage pi ON pi.product = p where pi.represent = true")
//    List<Object[]> getAllProductsWithImage();
//


//    @Query("SELECT p, pi FROM Product p LEFT JOIN ProductImage pi ON pi.product = p where p.productId = :id")
//    List<Object[]> getProductDetail(@Param("id") Long id);



}
