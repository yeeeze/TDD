package com.tdd.tdd.src.user.repository;

import com.tdd.tdd.src.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
