package com.unisoma_test.unisoma;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.unisoma_test.unisoma.dtos.EmployeeDto;
import com.unisoma_test.unisoma.exceptions.ConflictException;
import com.unisoma_test.unisoma.exceptions.NotFoundException;
import com.unisoma_test.unisoma.models.EmployeeModel;
import com.unisoma_test.unisoma.repositories.EmployeeRepository;
import com.unisoma_test.unisoma.services.EmployeeService;

@SpringBootTest
class EmployeeUnitTests {

	@InjectMocks
	private EmployeeService employeeService;

	@Mock
	private EmployeeRepository employeeRepository;

	@Test
	void givenRepeatedCpf_whenCreatingEmployee_thenThrowsError() {
		EmployeeDto employee = new EmployeeDto("Jonas", "224.353.324-24", "30-03-2000", "1274077007", "Rua Valdir, 28", new BigDecimal("2800"));
		doReturn(Optional.of(true)).when(employeeRepository).findByCpf(employee.getCpf());

		ConflictException exception = assertThrows(ConflictException.class, () -> employeeService.registerEmployee(employee));

		verify(employeeRepository, times(1)).findByCpf(employee.getCpf());
		verify(employeeRepository, times(0)).save(any());
		assertNotNull(exception);
		assertEquals("CPF de usuário já cadastrado.", exception.getMessage());
	}

	@Test
	void givenValidEmployee_whenCreatingEmployee_thenCreatesEmployee(){
		EmployeeDto employeeDto = new EmployeeDto("Jonas", "224.353.324-24", "30-03-2000", "1274077007", "Rua Valdir, 28", new BigDecimal("2800"));
		doReturn(Optional.empty()).when(employeeRepository).findByCpf(employeeDto.getCpf());


		EmployeeModel employee = new EmployeeModel(employeeDto);

		EmployeeModel result = employeeService.registerEmployee(employeeDto);

		verify(employeeRepository, times(1)).findByCpf(any());
		verify(employeeRepository, times(1)).save(employee);
		assertEquals(employee, result);

	}

	 @Test
    void givenNonExistentCpf_whenSearchEmployeeByCpf_thenTrhowsError(){
        String cpf = "012.345.678-90";
        doReturn(Optional.empty()).when(employeeRepository).findByCpf(cpf);

        NotFoundException exception = assertThrows(NotFoundException.class, () -> employeeService.findEmployeeByCpf(cpf));


        assertNotNull(exception);
        assertEquals("CPF de usuário não encontrado.", exception.getMessage());
        verify(employeeRepository, times(1)).findByCpf(cpf);

    }

	@Test
	void givenExistentCpf_whenSearcEmployeeByCpf_thenReturnsEmployee(){
		EmployeeDto employeeDto = new EmployeeDto("Jonas", "224.353.324-24", "30-03-2000", "1274077007", "Rua Valdir, 28", new BigDecimal("2800"));
		EmployeeModel createdEmployee = new EmployeeModel(employeeDto);
		employeeRepository.save(createdEmployee);
		doReturn(Optional.of(createdEmployee)).when(employeeRepository).findByCpf(employeeDto.getCpf());


		EmployeeModel result = employeeService.findEmployeeByCpf(employeeDto.getCpf());

		verify(employeeRepository, times(1)).findByCpf(any());
		assertEquals(createdEmployee, result);

	}

}
