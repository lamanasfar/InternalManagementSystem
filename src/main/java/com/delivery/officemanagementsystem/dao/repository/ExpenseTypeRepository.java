package com.delivery.officemanagementsystem.dao.repository;

import com.delivery.officemanagementsystem.dao.entity.ExpenseTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ExpenseTypeRepository extends JpaRepository<ExpenseTypeEntity, Long> {
    List<ExpenseTypeEntity> findAllByIsActiveTrue();
}