package com.employee.employee.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.employee.entity.Employee;
import com.employee.employee.entity.Task;

public interface TaskRepository extends JpaRepository <Task,Integer>
{
    public Optional<List<Task>> findByAssignedToId(int assignedToId);
    public void deleteByAssignedTo(Employee employee);
}
