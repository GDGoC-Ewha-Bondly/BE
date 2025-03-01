package com.gdgoc.bondly.repository;

import com.gdgoc.bondly.domain.SnsReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SnsReviewRepository extends JpaRepository<SnsReview, Long> {
    @Query("SELECT DISTINCT DATE_FORMAT(s.usageDate, '%Y-%m-%d') " +
            "FROM SnsUsage s WHERE FUNCTION('DATE_FORMAT', s.usageDate, '%Y-%m') = :month " +
            "AND s.user.id = :userId")
    List<String> findDatesByMonthAndUserId(@Param("month") String month, @Param("userId") Long userId);

    SnsReview findByDateAndUserId(String date, Long userId);
}
