package com.delivery.officemanagementsystem.mapper;

import com.delivery.officemanagementsystem.dao.entity.ExpenseEntity;
import com.delivery.officemanagementsystem.dao.entity.ExpenseTypeEntity;
import com.delivery.officemanagementsystem.dto.ExpenseDto;
import lombok.NoArgsConstructor;

 public class ExpenseMapper {

    public static ExpenseDto mapToExpenseDto(ExpenseEntity entity) {
        return ExpenseDto.builder()
                .id(entity.getId())
                .expenseTypeId(entity.getExpenseType() != null ? entity.getExpenseType().getId() : null)
                .expenseTypeName(entity.getExpenseType() != null ? entity.getExpenseType().getName() : null)
                .amount(entity.getAmount())
                .payer(entity.getPayer())
                .date(entity.getDate())
                .description(entity.getDescription())
                .receiptPath(entity.getReceiptPath())
                .build();
    }
    public static ExpenseEntity mapToExpenseEntity(
            ExpenseDto dto,
            ExpenseTypeEntity expenseType,
            String receiptPath
    ) {
        return ExpenseEntity.builder()
                .expenseType(expenseType)
                .amount(dto.getAmount())
                .payer(dto.getPayer())
                .date(dto.getDate())
                .description(dto.getDescription())
                .receiptPath(receiptPath)
                .isActive(true)
                .build();
    }
}