package com.example.bunjang.repository;

import com.example.bunjang.common.Role;
import com.example.bunjang.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    List<User> findByRole(Role role);

    Optional<User> findByNickName(String nickName);
}
