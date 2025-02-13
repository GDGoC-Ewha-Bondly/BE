package com.gdgoc.bondly.repository;

import com.gdgoc.bondly.domain.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
}
