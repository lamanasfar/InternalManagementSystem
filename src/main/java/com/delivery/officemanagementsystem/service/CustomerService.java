package com.delivery.officemanagementsystem.service;

import com.delivery.officemanagementsystem.dao.entity.CustomerEntity;
import com.delivery.officemanagementsystem.dao.repository.CustomerRepository;
import com.delivery.officemanagementsystem.dto.CustomerDto;
import com.delivery.officemanagementsystem.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public List<CustomerDto> getAllActiveCustomers() {
        List<CustomerEntity> activeCustomers = customerRepository.findAllByIsActiveTrue();
        return activeCustomers.stream()
                .map(CustomerMapper::mapToCustomerDto)
                .toList();
    }

    public CustomerDto createCustomer(CustomerDto customerDto) {
         var customerEntity = CustomerMapper.mapToCustomerToEntity(customerDto);
         return CustomerMapper.mapToCustomerDto(customerRepository.save(customerEntity));
    }

    public void updateCustomer(Long id, CustomerDto customerDto) {
        var existing = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
        CustomerMapper.updateEntityFromDto(customerDto, existing);
        customerRepository.save(existing);
    }

    public void deleteCustomer(Long id) {
        var customerEntity = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
        customerEntity.setIsActive(false);
        customerRepository.save(customerEntity);
    }


}