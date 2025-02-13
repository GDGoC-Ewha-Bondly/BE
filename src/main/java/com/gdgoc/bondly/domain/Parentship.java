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
@Table(name = "parentship")
public class Parentship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer parentshipId;
    @ManyToOne
    @JoinColumn(name = "child_id", nullable = false)
    private User child;
    @ManyToOne
    @JoinColumn(name = "parent_id", nullable = false)
    private User parent;
}

