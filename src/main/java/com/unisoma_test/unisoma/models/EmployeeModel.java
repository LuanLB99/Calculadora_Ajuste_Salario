package com.unisoma_test.unisoma.models;

import java.math.BigDecimal;


import com.unisoma_test.unisoma.dtos.EmployeeDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class EmployeeModel {

    
    public EmployeeModel(EmployeeDto employeeDto) {
        this.name = employeeDto.getName();
        this.cpf = employeeDto.getCpf();
        this.birthDay = employeeDto.getBirthDay();
        this.phone = employeeDto.getPhone();
        this.address = employeeDto.getAddress();
        this.salary = employeeDto.getSalary();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private String birthDay;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private BigDecimal salary;
}
