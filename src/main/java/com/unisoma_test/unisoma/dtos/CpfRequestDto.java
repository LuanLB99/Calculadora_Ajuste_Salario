package com.unisoma_test.unisoma.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CpfRequestDto {

    @NotBlank(message = "Insira o CPF")
    @Size(min = 14, max = 14, message = "O CPF deve conter 14 caracteres. Exemplo de CPF: XXX.XXX.XXX-XX ")
    private String cpf; 
}
