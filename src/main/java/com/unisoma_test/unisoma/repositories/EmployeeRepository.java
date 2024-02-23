package com.unisoma_test.unisoma.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unisoma_test.unisoma.models.EmployeeModel;

public interface EmployeeRepository extends JpaRepository <EmployeeModel, Long>  {
    public Optional<EmployeeModel> findByCpf(String cpf);
}
