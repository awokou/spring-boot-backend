package com.awokou.springbootbackend.mapper;

import com.awokou.springbootbackend.dto.EmployeeDTO;
import com.awokou.springbootbackend.model.Employee;

public class EmployeeMapper {
    public static Employee mapToEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setLastName(employeeDTO.getLastName());
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setEmail(employeeDTO.getEmail());

        return employee;
    }
    public static EmployeeDTO mapToEmployeeDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setLastName(employee.getLastName());
        employeeDTO.setFirstName(employee.getFirstName());
        employeeDTO.setEmail(employee.getEmail());

        return  employeeDTO;
    }
}
