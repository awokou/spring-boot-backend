package com.awokou.springbootbackend.service.impl;

import com.awokou.springbootbackend.dto.EmployeeDTO;
import com.awokou.springbootbackend.exception.ResourceNotFoundException;
import com.awokou.springbootbackend.mapper.EmployeeMapper;
import com.awokou.springbootbackend.model.Employee;
import com.awokou.springbootbackend.repository.EmployeeRepository;
import com.awokou.springbootbackend.service.EmployeeService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    private EmployeeRepository employeeRepository;
    private EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        log.info("In saveEmployee()");
        if (employeeDTO == null) {
            throw new IllegalArgumentException("employeeDTO must not be null");
        }
        checkIfEmployeeExists(employeeDTO);
        Employee employee = employeeMapper.mapToEmployee(employeeDTO);
        if (employee == null) {
            throw new IllegalArgumentException("employee must not be null, check mappers");
        }

        Employee savedEmployee = employeeRepository.save(employee);
        log.info("Employee saved successfully");
        return employeeMapper.mapToEmployeeDTO(savedEmployee);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        log.info("In getAllEmployees()");
        List<Employee> employees = employeeRepository.findAll();
        log.info("employee(s) found");
        return employees.stream().map((employee) -> employeeMapper.mapToEmployeeDTO(employee))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO getEmployeeById(long id) {
        log.info("In getEmployeeById()");
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee is not exists with given id : "+id));
        log.info("employee(s) not found");
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
        log.info("In updateEmployee()");
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Employee is not exists with given id:" +id));

        if (employeeDTO == null){
            throw new IllegalArgumentException("employeeDTO must not be null");
        }

        employee.setLastName(employeeDTO.getLastName());
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setEmail(employeeDTO.getEmail());

        Employee updateEmployee = employeeRepository.save(employee);
        log.info("employee updated successfully");
        return employeeMapper.mapToEmployeeDTO(updateEmployee);
    }

    @Override
    public List<EmployeeDTO> getEmployeeByEmail(String email) {
        log.info("In getEmployeeByEmail()");
        List<Employee> employees =employeeRepository.findByEmail(email);
        return employees.stream().map((employee) -> employeeMapper.mapToEmployeeDTO(employee))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteEmployeeById(long id) {
        // check whether a employee exist in a DB or not
        log.info("In deleteEmployeeById()");
        employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id :" + id));
        employeeRepository.deleteById(id);
        log.info("employee deleted successfully");
    }

    /**
     * check if employee already exist in database before save it.
     * @param employeeDTO The new employee information request
     */
    private void checkIfEmployeeExists(EmployeeDTO employeeDTO){
        if(employeeRepository.existsByEmail(employeeDTO.getEmail())) {
            throw new ResourceNotFoundException(String.format("Email  %s is already in use", employeeDTO.getEmail()));
        }
    }
}
