package com.example.bunjang.repository;

import com.example.bunjang.entity.Following;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FollowingRepository extends JpaRepository<Following, Long> {

    Optional<Following> findByUser_IdAndFollow_Id(Long userId, Long followId);

    void deleteByUser_IdAndFollow_Id(Long userId, Long followId);

    List<Following> findByUser_Id(Long userId);
    List<Following> findByFollow_Id(Long followId);

    Long countFollowingsByUser_Id(Long userId);

    Long countFollowingsByFollow_Id(Long followId);

}
