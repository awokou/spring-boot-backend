package com.awokou.springbootbackend.repository;

import com.awokou.springbootbackend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByEmail(String email);
    Optional<Employee> findByEmailIgnoreCase(String email);
    Boolean existsByEmail(String email);
}
