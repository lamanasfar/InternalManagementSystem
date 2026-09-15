package com.delivery.officemanagementsystem.dao.entity;

import com.delivery.officemanagementsystem.enums.CompanyName;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String objectName;
    private String address;
    private String contactPerson;
    private LocalDate birthDate;
    @Column(nullable = false)
    private LocalDate establishmentDate;
    @Column(nullable = false)
    private String phoneNumber;
    private String anydeskId;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CompanyName companyName;
    private Boolean isActive=true;

}
