package com.gdgoc.bondly.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "quiz_response")
public class QuizResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer responseId;
    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private QuizQuestion question;
    @ManyToOne
    @JoinColumn(name = "choice_id", nullable = false)
    private QuizChoice choice;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    private LocalDateTime responsedAt;
}