package com.gdgoc.bondly.repository;

import com.gdgoc.bondly.domain.SnsInsight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SnsInsightRepository extends JpaRepository<SnsInsight, Long> {
    @Query("SELECT DISTINCT DATE_FORMAT(s.usageDate, '%Y-%m-%d') " +
            "FROM SnsUsage s WHERE FUNCTION('DATE_FORMAT', s.usageDate, '%Y-%m') = :month " +
            "AND s.user.id = :userId")
    List<String> findDatesByMonthAndUserId(@Param("month") String month, @Param("userId") Long userId);

    SnsInsight findByDateAndUserId(String date, Long userId);
}
