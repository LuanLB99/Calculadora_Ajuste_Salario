package com.unisoma_test.unisoma.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeDto {

    @NotBlank(message = "Insira o nome do funcionário!")
    private String name;

    @NotBlank(message = "Insira o CPF")
    @Size(min = 14, max = 14, message = "O CPF deve conter 14 caracteres. Exemplo de CPF: XXX.XXX.XXX-XX ")
    private String cpf;

    @NotNull(message = "A data de aniversário não pode ser nula.")
    @Past(message = "A data de nascimento tem que ser no passado.")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthDay;
    
    @NotEmpty(message = "O telefone não pode estar vazio.")
    @Pattern(regexp = "\\d{11}", message = "O número de telefone deve conter exatamente 11 dígitos")
    private String phone;

    @NotBlank(message = "Insira um endereço válido.")
    private String address;

    @NotNull(message = "O salário não pode ser nulo")
    private BigDecimal salary;
}
