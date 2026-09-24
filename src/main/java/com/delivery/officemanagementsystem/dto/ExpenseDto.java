package com.delivery.officemanagementsystem.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExpenseDto {
    private Long id;
    private Long expenseTypeId;
    private String expenseTypeName;
    private BigDecimal amount;
    private String payer;
    private LocalDate date;
    private String description;
    private MultipartFile receiptFile;
    private String receiptPath;
}
