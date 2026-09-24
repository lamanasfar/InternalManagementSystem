package com.delivery.officemanagementsystem.service;


import com.delivery.officemanagementsystem.dao.entity.ExpenseEntity;
import com.delivery.officemanagementsystem.dao.entity.ExpenseTypeEntity;
import com.delivery.officemanagementsystem.dao.repository.ExpenseRepository;
import com.delivery.officemanagementsystem.dao.repository.ExpenseTypeRepository;
import com.delivery.officemanagementsystem.dto.ExpenseDto;
import com.delivery.officemanagementsystem.mapper.ExpenseMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ExpenseTypeRepository expenseTypeRepository;
    private final FileStorageService fileStorageService;

    private static final String RECEIPT_SUBDIR = "receipts";

    public List<ExpenseDto> getAllActiveExpenses() {
        return expenseRepository.findAllByIsActiveTrue().stream()
                .map(ExpenseMapper::mapToExpenseDto)
                .toList();
    }

    public void createExpense(ExpenseDto dto) {
        var expenseType = expenseTypeRepository.findById(dto.getExpenseTypeId())
                .orElseThrow(() -> new RuntimeException("Expense type not found with id: " + dto.getExpenseTypeId()));

        String receiptPath = fileStorageService.store(dto.getReceiptFile(), RECEIPT_SUBDIR);

        var expense = ExpenseMapper.mapToExpenseEntity(dto, expenseType, receiptPath);
        expenseRepository.save(expense);
    }

    public void updateExpense(Long id, ExpenseDto dto) {
        var existing = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found with id: " + id));

        var expenseType = expenseTypeRepository.findById(dto.getExpenseTypeId())
                .orElseThrow(() -> new RuntimeException("Expense type not found with id: " + dto.getExpenseTypeId()));

        existing.setExpenseType(expenseType);
        existing.setAmount(dto.getAmount());
        existing.setPayer(dto.getPayer());
        existing.setDate(dto.getDate());
        existing.setDescription(dto.getDescription());

        if (dto.getReceiptFile() != null && !dto.getReceiptFile().isEmpty()) {
            String receiptPath = fileStorageService.store(dto.getReceiptFile(), RECEIPT_SUBDIR);
            existing.setReceiptPath(receiptPath);
        }

        expenseRepository.save(existing);
    }

    public void deleteExpense(Long id) {
        var expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found with id: " + id));
        expense.setIsActive(false);
        expenseRepository.save(expense);
    }
}