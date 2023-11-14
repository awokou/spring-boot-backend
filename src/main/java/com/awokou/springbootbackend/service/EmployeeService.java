package com.awokou.springbootbackend.service;

import com.awokou.springbootbackend.dto.EmployeeDTO;

import java.util.List;


/**
 * Service interface for managing employee data.
 */
public interface EmployeeService {
    EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);
    List<EmployeeDTO> getAllEmployees();
    EmployeeDTO getEmployeeById(long id);
    EmployeeDTO updateEmployee(EmployeeDTO employeeDTO, long id);
    List<EmployeeDTO> getEmployeeByEmail(String email);
    void deleteEmployeeById(long id);
    boolean checkExistingEmail(String email);
}
