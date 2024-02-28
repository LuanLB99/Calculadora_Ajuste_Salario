package com.unisoma_test.unisoma.services;


import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.unisoma_test.unisoma.dtos.CpfRequestDto;
import com.unisoma_test.unisoma.dtos.IncomeTaxResponseDto;
import com.unisoma_test.unisoma.dtos.SalaryAdjustmentResponseDto;
import com.unisoma_test.unisoma.models.EmployeeModel;
import com.unisoma_test.unisoma.repositories.EmployeeRepository;

@Service
public class SalaryService {
    
    final EmployeeRepository employeeRepository;
    final EmployeeService employeeService;
    

    public SalaryService(EmployeeRepository employeeRepository, EmployeeService employeeService) {
        this.employeeRepository = employeeRepository;
        this.employeeService = employeeService;
    }

    public SalaryAdjustmentResponseDto adjustSalary(CpfRequestDto cpfDtoForm){

        EmployeeModel employee = employeeService.findEmployeeByCpf(cpfDtoForm.getCpf());
        BigDecimal salary = employee.getSalary();

        SalaryRate newSalary = new SalaryRate(salary);

        SalaryAdjustmentResponseDto newSalaryInformations = newSalary.makeAdjust();

        newSalaryInformations.setCpf(employee.getCpf());
        employee.setSalary(newSalaryInformations.getNewSalary());
        employeeRepository.save(employee);
     

        return newSalaryInformations;
    
    }

    public IncomeTaxResponseDto calculateIncomeTax(CpfRequestDto cpfDtoForm){
        EmployeeModel employee = employeeService.findEmployeeByCpf(cpfDtoForm.getCpf());
        BigDecimal salary = employee.getSalary();

        IncomeTaxCalculator employeeSalaryCalculator = new IncomeTaxCalculator(salary);
        IncomeTaxResponseDto salaryIncomeTaxInformation = employeeSalaryCalculator.calculateIncomeTax();
        salaryIncomeTaxInformation.setCpf(employee.getCpf());

        return salaryIncomeTaxInformation;

    }


    
}
