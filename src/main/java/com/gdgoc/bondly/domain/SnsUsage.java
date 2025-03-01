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
    private Long usageId;
    private LocalDate localDate;
    private Integer usageTime;

    @ManyToOne
    @JoinColumn(name = "app_id", nullable = false)
    private Long appId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;
}
