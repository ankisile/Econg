package com.example.bunjang.repository;

import com.example.bunjang.entity.Reward;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RewardRepository  extends JpaRepository<Reward, Long> {
    List<Reward> findByProject_Id(long id);
}
