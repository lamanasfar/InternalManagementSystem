package com.delivery.officemanagementsystem.controller;


import com.delivery.officemanagementsystem.dto.CustomerDto;
import com.delivery.officemanagementsystem.dto.ExpenseDto;
import com.delivery.officemanagementsystem.dto.ExpenseTypeDto;
import com.delivery.officemanagementsystem.service.ExpenseService;
import com.delivery.officemanagementsystem.service.ExpenseTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;
    private final ExpenseTypeService expenseTypeService;

    @GetMapping
    public String getExpensesPage(Model model) {
        if (!model.containsAttribute("expense")) {
            model.addAttribute("expense", new ExpenseDto());
        }
        if (!model.containsAttribute("expenseType")) {
            model.addAttribute("expenseType", new ExpenseTypeDto());
        }
        model.addAttribute("expenseTypes", expenseTypeService.getAllActiveExpenseTypes());
        model.addAttribute("expenses", expenseService.getAllActiveExpenses());
        return "expenses";
    }

    @PostMapping(value = "/add", consumes = "multipart/form-data")
    public String createExpense(@ModelAttribute("expense") ExpenseDto expenseDto,
                                RedirectAttributes redirectAttributes) {
        expenseService.createExpense(expenseDto);
        redirectAttributes.addFlashAttribute("message", "Xərc uğurla əlavə edildi!");
        return "redirect:/expenses";
    }

    @PostMapping(value = "/edit/{id}", consumes = "multipart/form-data")
    public String updateExpense(@PathVariable Long id,
                                @ModelAttribute("expense") ExpenseDto expenseDto,
                                RedirectAttributes redirectAttributes) {
        expenseService.updateExpense(id, expenseDto);
        redirectAttributes.addFlashAttribute("message", "Xərc yeniləndi!");
        return "redirect:/expenses";
    }

    @PostMapping("/delete/{id}")
    public String deleteExpense(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        expenseService.deleteExpense(id);
        redirectAttributes.addFlashAttribute("message", "Xərc silindi!");
        return "redirect:/expenses";
    }
}

