package com.unisoma_test.unisoma.dtos;

import java.math.BigDecimal;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
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
    private String birthDay;
    
    @NotEmpty(message = "O telefone não pode estar vazio.")
    @Pattern(regexp = "\\d{11}", message = "O número de telefone deve conter exatamente 11 dígitos")
    private String phone;

    @NotBlank(message = "Insira um endereço válido.")
    private String address;

    @NotNull(message = "O salário não pode ser nulo")
    @Positive(message = "O salário deve ser maior que zero")
    private BigDecimal salary;
}
