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
@Table(name = "sns_insight")
public class SnsInsight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer insightId;
    private LocalDate date;
    private String insightContent;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}