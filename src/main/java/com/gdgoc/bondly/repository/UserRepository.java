package com.gdgoc.bondly.repository;

import com.gdgoc.bondly.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    int findUserGoalByUserId(Long userId);
}
