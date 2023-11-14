package com.awokou.springbootbackend.service.impl;

import com.awokou.springbootbackend.dto.EmployeeDTO;
import com.awokou.springbootbackend.exception.ResourceNotFoundException;
import com.awokou.springbootbackend.mapper.EmployeeMapper;
import com.awokou.springbootbackend.model.Employee;
import com.awokou.springbootbackend.repository.EmployeeRepository;
import com.awokou.springbootbackend.service.EmployeeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        checkIfEmployeeExists(employeeDTO);
        Employee employee = employeeMapper.mapToEmployee(employeeDTO);
        if (employee == null) {
            throw new IllegalArgumentException("employee must not be null");
        }

        Employee savedEmployee = employeeRepository.save(employee);
        return employeeMapper.mapToEmployeeDTO(savedEmployee);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll().stream().map((employee) -> employeeMapper.mapToEmployeeDTO(employee))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO getEmployeeById(long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee is not exists with given id : "+id));
        return employeeMapper.mapToEmployeeDTO(employee);
    }

    /**
     * Updates an existing employee's information.
     *
     * @param id          The unique identifier of the employee to be updated.
     * @param employeeDTO The {@link EmployeeDTO} containing the updated employee information.
     * @return The {@link EmployeeDTO} representing the updated employee.
     */
    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO, long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Employee is not exists with given id:" +id));

        if (employeeDTO == null){
            throw new IllegalArgumentException("employeeDTO must not be null");
        }

        employee.setLastName(employeeDTO.getLastName());
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setEmail(employeeDTO.getEmail());
        Employee updateEmployee = employeeRepository.save(employee);

        return employeeMapper.mapToEmployeeDTO(updateEmployee);
    }

    @Override
    public List<EmployeeDTO> getEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email)
                .stream().map((employee) -> employeeMapper.mapToEmployeeDTO(employee))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteEmployeeById(long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public boolean checkExistingEmail(String email) {
        boolean existing = false;
        Optional<Employee> employee = employeeRepository.findByEmailIgnoreCase(email);
        if (employee.isPresent()) {
            existing = true;
        }
        return existing;
    }

    /**
     * check if employee already exist in database before save it.
     * @param employeeDTO The new employee information request
     */
    private void checkIfEmployeeExists(EmployeeDTO employeeDTO){
        Boolean employeeExists = employeeRepository.existsByEmail(employeeDTO.getEmail());
        if(Boolean.TRUE.equals(employeeExists)) {
            throw new ResourceNotFoundException(String.format("Email  %s is already in use", employeeDTO.getEmail()));
        }
    }
}
