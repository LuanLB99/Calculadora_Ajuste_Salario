package com.unisoma_test.unisoma.dtos;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IncomeTaxResponseDto {

    public IncomeTaxResponseDto(BigDecimal taxToPay) {
        this.taxToPay = setTaxToPay(taxToPay);
    }
    
    private String cpf;
    private String taxToPay;


    public String setTaxToPay(BigDecimal taxToPay){
        if(taxToPay.compareTo(BigDecimal.ZERO) <= 0){
            return "Isento";
        }
        
        return "R$ " + taxToPay;
    }
}
