package com.employee.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.employee.entity.Task;

public interface TaskRepository extends JpaRepository <Task,Integer>
{

    List<Task> findByAssignedToId(int assignedToId);

    
    
    
}
