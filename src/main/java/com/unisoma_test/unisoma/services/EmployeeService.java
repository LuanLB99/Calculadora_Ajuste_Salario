package com.unisoma_test.unisoma.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.unisoma_test.unisoma.dtos.EmployeeDto;
import com.unisoma_test.unisoma.exceptions.ConflictException;
import com.unisoma_test.unisoma.exceptions.NotFoundException;
import com.unisoma_test.unisoma.models.EmployeeModel;
import com.unisoma_test.unisoma.repositories.EmployeeRepository;

@Service
public class EmployeeService {
    final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    public EmployeeModel registerEmployee(EmployeeDto employeeDto){
        Optional<EmployeeModel> haveEmployee =  employeeRepository.findByCpf(employeeDto.getCpf());
        if(haveEmployee.isPresent()){
            throw new ConflictException("CPF de usuário já cadastrado.");
        }
        EmployeeModel employee = new EmployeeModel(employeeDto);
        employeeRepository.save(employee);

        return employee;

    }

    public EmployeeModel findEmployeeByCpf(String cpf){
        Optional<EmployeeModel> haveEmployee =  employeeRepository.findByCpf(cpf);
        if(!haveEmployee.isPresent()){
            throw new NotFoundException("CPF de usuário não encontrado.");
        }
        EmployeeModel employee = haveEmployee.get();
        return employee;
    }


}
