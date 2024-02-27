package com.unisoma_test.unisoma.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

import com.unisoma_test.unisoma.dtos.IncomeTaxResponseDto;

import lombok.Data;

@Data
public class IncomeTaxCalculator {
    
    public IncomeTaxCalculator(BigDecimal employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    BigDecimal employeeSalary;

     private static class TaxRange {
        BigDecimal min;
        BigDecimal max;
        BigDecimal rate;

        TaxRange(BigDecimal min, BigDecimal max, BigDecimal rate) {
            this.min = min;
            this.max = max;
            this.rate = rate;
        }
    }

    private static final List<TaxRange> TAX_RANGES = Arrays.asList(
            new TaxRange(BigDecimal.ZERO, new BigDecimal("2000"), BigDecimal.ZERO),
            new TaxRange(new BigDecimal("2000.01"), new BigDecimal("3000"), BigDecimal.valueOf(0.08)),
            new TaxRange(new BigDecimal("3000.01"), new BigDecimal("4500"), BigDecimal.valueOf(0.18)),
            new TaxRange(new BigDecimal("4500.01"), new BigDecimal("20000"), BigDecimal.valueOf(0.28))
    );

    public IncomeTaxResponseDto calculateIncomeTax() {
        BigDecimal totalTax = BigDecimal.ZERO;
        BigDecimal remainingSalary = employeeSalary;

        for (TaxRange range : TAX_RANGES) {
            if (remainingSalary.compareTo(BigDecimal.ZERO) <= 0) {
                break;
            }
   
            BigDecimal taxableAmount = getTaxableAmount(remainingSalary, range);
            BigDecimal tax = taxableAmount.multiply(range.rate);
            totalTax = totalTax.add(tax);
            remainingSalary = remainingSalary.subtract(taxableAmount);
        }

        return new IncomeTaxResponseDto(totalTax.setScale(2, RoundingMode.HALF_EVEN));
    }

    private BigDecimal getTaxableAmount(BigDecimal employeeSalary, TaxRange range){

        BigDecimal rangeDifference = range.max.subtract(range.min);

        return employeeSalary.min(rangeDifference);
    }
}
