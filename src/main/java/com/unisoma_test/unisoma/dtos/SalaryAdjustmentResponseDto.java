package com.unisoma_test.unisoma.dtos;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SalaryAdjustmentResponseDto {
    
    private String cpf;
    private BigDecimal newSalary;
    private BigDecimal adjustment;
    private String adjustmentRate;


    public SalaryAdjustmentResponseDto( BigDecimal newSalary, BigDecimal adjustment,
            BigDecimal adjustmentRate) {
        this.newSalary = newSalary;
        this.adjustment = adjustment;
        this.adjustmentRate = setAdjustmentRate(adjustmentRate);
    }
    
    public String setAdjustmentRate(BigDecimal adjustmentRate){
        DecimalFormat percentageFormat = new DecimalFormat("0.##%");

        return  percentageFormat.format(adjustmentRate);
    }

    


}
