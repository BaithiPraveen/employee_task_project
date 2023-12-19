package com.employee.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.employee.entity.Employee;

public interface EmployeeRepository extends JpaRepository <Employee,Integer>
{
    public List<Employee> findByName(String name);
    public List<Employee> findByDept(String dept);
    public List<Employee> findByNameAndDept(String name,String Dept);
}
