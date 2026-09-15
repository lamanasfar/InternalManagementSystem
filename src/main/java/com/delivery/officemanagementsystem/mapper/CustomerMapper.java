package com.delivery.officemanagementsystem.mapper;

import com.delivery.officemanagementsystem.dao.entity.CustomerEntity;
import com.delivery.officemanagementsystem.dto.CustomerDto;




public class CustomerMapper {

    public static CustomerEntity mapToCustomerToEntity(CustomerDto dto) {
        CustomerEntity entity = new CustomerEntity();
        entity.setId(dto.getId());
        entity.setObjectName(dto.getObjectName());
        entity.setAddress(dto.getAddress());
        entity.setContactPerson(dto.getContactPerson());
        entity.setBirthDate(dto.getBirthDate());
        entity.setEstablishmentDate(dto.getEstablishmentDate());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setAnydeskId(dto.getAnydeskId());
        entity.setCompanyName(dto.getCompanyName());
        entity.setIsActive(dto.getIsActive());

        return entity;
    }

    public static CustomerDto mapToCustomerDto(CustomerEntity entity) {
        CustomerDto dto = new CustomerDto();
        dto.setId(entity.getId());
        dto.setObjectName(entity.getObjectName());
        dto.setAddress(entity.getAddress());
        dto.setContactPerson(entity.getContactPerson());
        dto.setBirthDate(entity.getBirthDate());
        dto.setEstablishmentDate(entity.getEstablishmentDate());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setAnydeskId(entity.getAnydeskId());
        dto.setCompanyName(entity.getCompanyName());
        dto.setIsActive(entity.getIsActive());
        return dto;
    }
    public static void updateEntityFromDto(CustomerDto dto, CustomerEntity entity) {
        entity.setObjectName(dto.getObjectName());
        entity.setAddress(dto.getAddress());
        entity.setContactPerson(dto.getContactPerson());
        entity.setBirthDate(dto.getBirthDate());
        entity.setEstablishmentDate(dto.getEstablishmentDate());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setAnydeskId(dto.getAnydeskId());
        entity.setCompanyName(dto.getCompanyName());
    }
}


