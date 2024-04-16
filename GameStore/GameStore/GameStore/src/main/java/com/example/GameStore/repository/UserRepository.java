package com.example.GameStore.repository;

import com.example.GameStore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUserName(String username);

    boolean existsByEmail(String email);

    Optional<User> findByUserName(String userName);
}
