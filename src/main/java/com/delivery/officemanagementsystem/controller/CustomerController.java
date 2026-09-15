package com.delivery.officemanagementsystem.controller;


import com.delivery.officemanagementsystem.dto.CustomerDto;
import com.delivery.officemanagementsystem.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public String getCustomersPage(Model model) {
        if (!model.containsAttribute("customer")) {
            model.addAttribute("customer", new CustomerDto());
        }
        model.addAttribute("customers", customerService.getAllActiveCustomers());
        return "customers";
    }

    @PostMapping("/add")
    public String createCustomer(@ModelAttribute("customer") CustomerDto customerDto,
                                 RedirectAttributes redirectAttributes) {
        customerService.createCustomer(customerDto);
        redirectAttributes.addFlashAttribute("message", "Müştəri uğurla yaradıldı!");

        return "redirect:/customers";
    }

    @PostMapping("/edit/{id}")
    public String updateCustomer(@PathVariable Long id,
                                 @ModelAttribute CustomerDto customerDto,
                                 RedirectAttributes redirectAttributes) {
        customerService.updateCustomer(id, customerDto);
        redirectAttributes.addFlashAttribute("message", "Müştəri uğurla yeniləndi!");
        return "redirect:/customers";
    }

    @PostMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        customerService.deleteCustomer(id);
        redirectAttributes.addFlashAttribute("message", "Müştəri uğurla silindi!");
        return "redirect:/customers";
    }
}



