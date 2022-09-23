package com.example.bunjang.repository;

import com.example.bunjang.entity.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FavoritesRepository extends JpaRepository<Favorites, Long> {
    Optional<Favorites> findByUser_IdAndProject_Id(Long userId, Long projectId);

    List<Favorites> findByUser_Id(Long userId);


}
