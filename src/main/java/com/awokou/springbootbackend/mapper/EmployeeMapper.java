package com.awokou.springbootbackend.mapper;

import com.awokou.springbootbackend.dto.EmployeeDTO;
import com.awokou.springbootbackend.model.Employee;
import org.springframework.stereotype.Service;

@Service
public class EmployeeMapper {

    /**
     * Converts a {@link EmployeeDTO} object to a corresponding {@link Employee} object.
     *
     * @param employeeDTO The {@link EmployeeDTO} object to convert.
     * @return The resulting {@link Employee} object.
     */
    public Employee mapToEmployee(EmployeeDTO employeeDTO) {
        if(employeeDTO == null){
            return null;
        }
        return new Employee(
                employeeDTO.getId(),
                employeeDTO.getLastName(),
                employeeDTO.getFirstName(),
                employeeDTO.getEmail());
    }

    /**
     * Converts a {@link Employee} object to a corresponding {@link EmployeeDTO} object.
     *
     * @param employee The {@link Employee} object to convert.
     * @return The resulting {@link EmployeeDTO} object.
     */
    public EmployeeDTO mapToEmployeeDTO(Employee employee) {
        if(employee == null){
            return null;
        }
        return new EmployeeDTO(
                employee.getId(),
                employee.getLastName(),
                employee.getFirstName(),
                employee.getEmail());
    }
}
