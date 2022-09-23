package com.example.bunjang.repository;

import com.example.bunjang.entity.Community;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommunityRepository extends JpaRepository<Community, Long> {

    List<Community> findByProject_IdOrderByUpdatedAt(Long projectId);

    List<Community> findByUser_EmailOrderByUpdatedAt(String userEmail);
}
