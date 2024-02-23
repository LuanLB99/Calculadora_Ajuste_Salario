package com.unisoma_test.unisoma.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.unisoma_test.unisoma.dtos.EmployeeDto;
import com.unisoma_test.unisoma.models.EmployeeModel;
import com.unisoma_test.unisoma.services.EmployeeService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;





@RestController
@RequestMapping("/api")
public class EmployeeController {

    final EmployeeService employeeService;

    
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/health")
    public String getMethodName() {
        return "Ok! It's Fine!";
    }

    @GetMapping("/employee")
    public ResponseEntity<Object> listEmployees() {
        List<EmployeeModel> employees = employeeService.listEmployees();

        return ResponseEntity.status(HttpStatus.OK).body(employees);
    }
    

    @PostMapping("/employee")
    public ResponseEntity<Object> employeeRegister(@RequestBody @Valid EmployeeDto dtoForm, BindingResult bindingResult) {

            if(bindingResult.hasErrors()){
                String errorMessage = "Erro de validação: " + bindingResult.getFieldError().getDefaultMessage();
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
            }
            
            EmployeeModel employee = employeeService.registerEmployee(dtoForm);
            return ResponseEntity.status(HttpStatus.CREATED).body(employee);
        
    }
    


}
