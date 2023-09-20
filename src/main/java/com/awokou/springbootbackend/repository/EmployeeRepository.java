package com.awokou.springbootbackend.repository;

import com.awokou.springbootbackend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Boolean existsByEmail(String email);
}
