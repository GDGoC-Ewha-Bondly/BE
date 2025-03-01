package com.gdgoc.bondly.repository;

import com.gdgoc.bondly.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
