package com.delivery.officemanagementsystem.controller;

import com.delivery.officemanagementsystem.dto.CustomerDto;
import com.delivery.officemanagementsystem.dto.ExpenseTypeDto;
import com.delivery.officemanagementsystem.service.ExpenseTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/expenses/types")
public class ExpenseTypeController {

    private final ExpenseTypeService expenseTypeService;

    @PostMapping("/add")
    public String createExpenseType(@ModelAttribute("expenseType") ExpenseTypeDto expenseTypeDto,
                                    RedirectAttributes redirectAttributes) {
        expenseTypeService.createExpenseType(expenseTypeDto);
        redirectAttributes.addFlashAttribute("message", "Xərc növü uğurla yaradıldı!");
        return "redirect:/expenses";
    }

    @PostMapping("/delete/{id}")
    public String deleteExpenseType(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        expenseTypeService.deleteExpenseType(id);
        redirectAttributes.addFlashAttribute("message", "Xərc növü silindi!");
        return "redirect:/expenses";
    }
}
