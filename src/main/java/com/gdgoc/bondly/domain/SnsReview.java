package com.gdgoc.bondly.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sns_review")
public class SnsReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;
    private LocalDate localDate;
    private String reviewContent;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;
}