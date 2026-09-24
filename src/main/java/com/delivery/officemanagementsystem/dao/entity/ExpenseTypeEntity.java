package com.delivery.officemanagementsystem.dao.entity;

import jakarta.persistence.Table;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "expense_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ExpenseTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private Boolean isActive = true;
}