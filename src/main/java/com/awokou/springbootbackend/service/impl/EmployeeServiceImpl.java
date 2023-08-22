package com.awokou.springbootbackend.service.impl;

import com.awokou.springbootbackend.exception.ResourceNotFoundException;
import com.awokou.springbootbackend.model.Employee;
import com.awokou.springbootbackend.repository.EmployeeRepository;
import com.awokou.springbootbackend.service.EmployeeService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        super();
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id :" + id));

    }

    @Override
    public Employee updateEmployee(Employee employee, long id) {
        // we need to check whether employee with given id is exist in DB or not
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id :" + id));

        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());
        // save existing employee to DB
        employeeRepository.save(existingEmployee);
        return existingEmployee;
    }

    @Override
    public void deleteEmployee(long id) {
        // check whether a employee exist in a DB or not
        employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id :" + id));
        employeeRepository.deleteById(id);
    }
}
