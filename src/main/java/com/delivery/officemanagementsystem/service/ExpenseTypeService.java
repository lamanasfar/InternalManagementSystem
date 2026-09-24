package com.delivery.officemanagementsystem.service;

import com.delivery.officemanagementsystem.dao.entity.ExpenseTypeEntity;
import com.delivery.officemanagementsystem.dao.repository.ExpenseTypeRepository;
import com.delivery.officemanagementsystem.dto.ExpenseTypeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseTypeService {
    private final ExpenseTypeRepository expenseTypeRepository;

    public List<ExpenseTypeDto> getAllActiveExpenseTypes() {
        return expenseTypeRepository.findAllByIsActiveTrue().stream()
                .map(expenseType -> ExpenseTypeDto.builder()
                        .id(expenseType.getId())
                        .name(expenseType.getName())
                        .build())
                .toList();

    }

    public ExpenseTypeDto createExpenseType(ExpenseTypeDto expenseTypeDto) {

        var expenseType = ExpenseTypeEntity.builder()
                .name(expenseTypeDto.getName())
                .isActive(true)
                .build();

        var savedExpenseType = expenseTypeRepository.save(expenseType);

        return ExpenseTypeDto.builder()
                .id(savedExpenseType.getId())
                .name(savedExpenseType.getName())
                .build();
    }
    public void deleteExpenseType(Long id) {
        var expenseType = expenseTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense type not found with id: " + id));//global exception

        expenseType.setIsActive(false);
        expenseTypeRepository.save(expenseType);
    }




    }
