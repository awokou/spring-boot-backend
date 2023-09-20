package com.awokou.springbootbackend.service.impl;

import com.awokou.springbootbackend.dto.EmployeeDTO;
import com.awokou.springbootbackend.exception.ResourceNotFoundException;
import com.awokou.springbootbackend.mapper.EmployeeMapper;
import com.awokou.springbootbackend.model.Employee;
import com.awokou.springbootbackend.repository.EmployeeRepository;
import com.awokou.springbootbackend.service.EmployeeService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        if(employeeRepository.existsByEmail(employeeDTO.getEmail())) {
            throw new ResourceNotFoundException(String.format("Email  %s is already in use", employeeDTO.getEmail()));
        }

        Employee employee = EmployeeMapper.mapToEmployee(employeeDTO);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDTO(savedEmployee);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDTO(employee))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO getEmployeeById(long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee is not exists with given id : "+id));

        return EmployeeMapper.mapToEmployeeDTO(employee);
    }

    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO, long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Employee is not exists with given id:" +id));
        employee.setLastName(employeeDTO.getLastName());
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setEmail(employeeDTO.getEmail());
        Employee updateEmployee = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDTO(updateEmployee);
    }

    @Override
    public void deleteEmployee(long id) {
        // check whether a employee exist in a DB or not
        employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id :" + id));
        employeeRepository.deleteById(id);
    }
}
