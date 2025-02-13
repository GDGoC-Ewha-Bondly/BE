package com.gdgoc.bondly.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "quiz_choice")
public class QuizChoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer choiceId;
    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private QuizQuestion question;
    private String choiceContent;
    private Boolean isCorrect;
}