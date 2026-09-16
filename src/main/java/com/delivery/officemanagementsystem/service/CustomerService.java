package com.delivery.officemanagementsystem.service;

import com.delivery.officemanagementsystem.common.pagination.PaginationRequestDto;
import com.delivery.officemanagementsystem.common.specification.SearchSpecification;
import com.delivery.officemanagementsystem.dao.entity.CustomerEntity;
import com.delivery.officemanagementsystem.dao.repository.CustomerRepository;
import com.delivery.officemanagementsystem.dto.CustomerDto;
import com.delivery.officemanagementsystem.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Page<CustomerDto> getActiveCustomers(PaginationRequestDto request) {

        Pageable pageable = PageRequest.of(
                request.page(),
                PaginationRequestDto.DEFAULT_SIZE
        );

        Specification<CustomerEntity> specification =
                SearchSpecification.search(
                        request.search(),
                        "objectName",
                        "contactPerson",
                        "companyName"


                );

        specification = specification.and(
                (root, query, criteriaBuilder) ->
                        criteriaBuilder.isTrue(root.get("isActive"))
        );

        Page<CustomerEntity> customerPage =
                customerRepository.findAll(
                        specification,
                        pageable
                );

        return customerPage.map(CustomerMapper::mapToCustomerDto);
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