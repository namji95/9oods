package com.kuku9.goods.domain.user.repository;

import com.kuku9.goods.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String Username);

    boolean existsByUsername(String username);
}
