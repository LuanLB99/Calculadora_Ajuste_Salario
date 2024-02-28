package com.unisoma_test.unisoma.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

import com.unisoma_test.unisoma.dtos.SalaryAdjustmentResponseDto;

import lombok.Data;

@Data
public class SalaryRate {

    private BigDecimal employeeSalary;

    public SalaryRate(BigDecimal employeeSalary) {
        this.employeeSalary = employeeSalary;
    }
    
    private static class SalaryRange {
        BigDecimal min;
        BigDecimal max;
        BigDecimal rate;

        SalaryRange(BigDecimal min, BigDecimal max, BigDecimal rate) {
            this.min = min;
            this.max = max;
            this.rate = rate;
        }
    }

    private static final List<SalaryRange> SALARY_RANGES = Arrays.asList(
            new SalaryRange(BigDecimal.ZERO, new BigDecimal("400"), BigDecimal.valueOf(0.15)),
            new SalaryRange(new BigDecimal("400.01"), new BigDecimal("800"), BigDecimal.valueOf(0.12)),
            new SalaryRange(new BigDecimal("800.01"), new BigDecimal("1200"), BigDecimal.valueOf(0.10)),
            new SalaryRange(new BigDecimal("1200.01"), new BigDecimal("2000"), BigDecimal.valueOf(0.07)),
            new SalaryRange(new BigDecimal("2000.01"), null, BigDecimal.valueOf(0.04))
    );

    public SalaryAdjustmentResponseDto makeAdjust(){
            for (SalaryRange range : SALARY_RANGES){
                if(isInRange(employeeSalary, range)){
                    BigDecimal adjustment = employeeSalary.multiply(range.rate);
                        return new SalaryAdjustmentResponseDto(employeeSalary.add(adjustment).setScale(2, RoundingMode.HALF_EVEN), adjustment.setScale(2, RoundingMode.HALF_EVEN), range.rate);
                }
            }
            
            return new SalaryAdjustmentResponseDto();
    }

    private boolean isInRange(BigDecimal salary, SalaryRange range) {
        return (range.min.compareTo(salary) <= 0) && (range.max == null || range.max.compareTo(salary) >= 0);
    }

}
