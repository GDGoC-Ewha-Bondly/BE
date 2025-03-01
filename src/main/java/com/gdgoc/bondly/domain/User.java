package com.gdgoc.bondly.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String name;
    private String phone;

    @Column(unique = true)
    private String id;

    private String password;
    private LocalDate birthday;
    private LocalDateTime registeredAt;
    private Boolean isParent;
    private Integer code;
    private Integer usage_goal;
}














