package com.delivery.officemanagementsystem.dao.repository;

 import com.delivery.officemanagementsystem.dao.entity.ExpenseEntity;
 import org.springframework.data.jpa.repository.EntityGraph;
 import org.springframework.data.jpa.repository.JpaRepository;
 import org.springframework.stereotype.Repository;

 import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<ExpenseEntity, Long>{
 @EntityGraph(attributePaths = {"expenseType"})
 List<ExpenseEntity> findAllByIsActiveTrue();

}