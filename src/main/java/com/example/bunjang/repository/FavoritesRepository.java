package com.example.bunjang.repository;

import com.example.bunjang.entity.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FavoritesRepository extends JpaRepository<Favorites, Long> {
    Optional<Favorites> findByUser_UserIdAndProduct_ProductId(Long userId, Long productId);
}
