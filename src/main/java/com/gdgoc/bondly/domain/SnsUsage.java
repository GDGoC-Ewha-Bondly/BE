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
@Table(name = "sns_usage")
public class SnsUsage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer usageId;
    private LocalDate date;
    private String appId;
    private Integer usageTime;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
