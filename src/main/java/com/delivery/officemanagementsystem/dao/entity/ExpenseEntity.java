package com.delivery.officemanagementsystem.dao.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "expenses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExpenseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expense_type_id", nullable = false)
    private ExpenseTypeEntity expenseType;
    @Column(nullable = false)
    private BigDecimal amount;
    private String payer;
    @Column(nullable = false)
    private LocalDate date;
    private String description;
    private String receiptPath;
    private Boolean isActive = true;
}
