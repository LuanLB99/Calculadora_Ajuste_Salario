package com.unisoma_test.unisoma;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.unisoma_test.unisoma.dtos.CpfRequestDto;
import com.unisoma_test.unisoma.dtos.IncomeTaxResponseDto;
import com.unisoma_test.unisoma.dtos.SalaryAdjustmentResponseDto;
import com.unisoma_test.unisoma.models.EmployeeModel;
import com.unisoma_test.unisoma.repositories.EmployeeRepository;
import com.unisoma_test.unisoma.services.EmployeeService;
import com.unisoma_test.unisoma.services.IncomeTaxCalculator;
import com.unisoma_test.unisoma.services.SalaryRate;
import com.unisoma_test.unisoma.services.SalaryService;

@SpringBootTest
class SalaryUnitTests {
    
    @InjectMocks
    private SalaryService salaryService;

    @Mock
    private EmployeeService employeeService;
    
    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    void givenEmployeeCpf_whenAdjustSalary_returnSalaryAdjusted(){
        String cpf = "224.353.324-24";
        BigDecimal initialSalary = new BigDecimal("2800");

        EmployeeModel createdEmployee = new EmployeeModel();
        createdEmployee.setCpf(cpf);
        createdEmployee.setSalary(initialSalary);
        doReturn(createdEmployee).when(employeeService).findEmployeeByCpf(cpf);

        SalaryRate createdSalaryRate = new SalaryRate(initialSalary);
        SalaryAdjustmentResponseDto adjustedSalary = createdSalaryRate.makeAdjust();
        adjustedSalary.setCpf(cpf);

        CpfRequestDto cpfRequestDto = new CpfRequestDto();
        cpfRequestDto.setCpf(cpf);
        SalaryAdjustmentResponseDto result = salaryService.adjustSalary(cpfRequestDto);

        assertNotNull(result);
        assertEquals(adjustedSalary, result);
        verify(employeeService, times(1)).findEmployeeByCpf(cpf);
        
    }

    @Test
    void givenEmployeeCpf_whenCalculateIncomeTax_returnCalculatedTax(){
        String cpf = "224.353.324-24";
        BigDecimal initialSalary = new BigDecimal("2800");

        EmployeeModel createdEmployee = new EmployeeModel();
        createdEmployee.setCpf(cpf);
        createdEmployee.setSalary(initialSalary);
        doReturn(createdEmployee).when(employeeService).findEmployeeByCpf(cpf);

         IncomeTaxCalculator employeeSalaryCalculator = new IncomeTaxCalculator(initialSalary);
         IncomeTaxResponseDto salaryIncomeTaxInformation = employeeSalaryCalculator.calculateIncomeTax();
         salaryIncomeTaxInformation.setCpf(cpf);

         CpfRequestDto cpfRequestDto = new CpfRequestDto();
        cpfRequestDto.setCpf(cpf);
        IncomeTaxResponseDto result = salaryService.calculateIncomeTax(cpfRequestDto);

        assertNotNull(result);
        assertEquals(salaryIncomeTaxInformation, result);
        verify(employeeService, times(1)).findEmployeeByCpf(cpf);

     }


}
