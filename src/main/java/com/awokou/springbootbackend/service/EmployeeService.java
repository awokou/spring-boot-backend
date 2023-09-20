package com.awokou.springbootbackend.service;

import com.awokou.springbootbackend.dto.EmployeeDTO;
import com.awokou.springbootbackend.model.Employee;

import java.util.List;

public interface EmployeeService {
    EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);
    List<EmployeeDTO> getAllEmployees();
    EmployeeDTO getEmployeeById(long id);
    EmployeeDTO updateEmployee(EmployeeDTO employeeDTO, long id);
    void deleteEmployee(long id);
}
