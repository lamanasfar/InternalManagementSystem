package com.delivery.officemanagementsystem.dto;

import com.delivery.officemanagementsystem.enums.CompanyName;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Data
public class CustomerDto {
    private Long id;
    @Column(name = "obyekt_adi", nullable = false)
    private String objectName;
    @Column (name = "adres")
    private String address;
    @Column(name = "elaqeli_shexsi", nullable = false)
    private String contactPerson;
    private LocalDate birthDate;
    @Column(name = "qurulma_tarixi", nullable = false)
    private LocalDate establishmentDate;
    @Column(name = "telefon_nomresi", nullable = false)
    private String phoneNumber;
    @Column(name = "anydesk")
    private String anydeskId;
    @Column(name = "sirket_adi", nullable = false)
    private CompanyName companyName;
    private Boolean isActive = true;
}
