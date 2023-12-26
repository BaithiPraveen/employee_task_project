package com.employee.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// import com.employee.employee.repository.EmployeeRepository;

// import org.springframework.beans.factory.annotation.Autowired;

import com.employee.employee.repository.TaskRepository;
// import com.employee.employee.entity.Employee;
import com.employee.employee.entity.Task;

@Service
public class TaskService 
{
    @Autowired
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(int id) {
        return taskRepository.findById(id);
    }

    public Task saveTask(Task task)
    {
        try
        {
            // Employee employee_id = task.getAssignedTo();
            // System.out.println(employee_id+ " "+employee_id.getId());
            // Optional<Employee> emp_id_exist = employeeService.getEmployeeById(employee_id.getId());
            // System.out.println("iam in try in service ..@");
            // if (emp_id_exist.isPresent())
                return taskRepository.save(task);
            // else return null;
        }
        catch(Exception ex)
        {
            System.out.println("iam in service method..!");
            return null;
        }
    }

    public String deleteTask(int id) {
        String task_desc = getTaskById(id).get().getTaskDescription();
        taskRepository.deleteById(id);
        return String.format("%s : Task is deleted succfully..!",task_desc);
    }

    public Optional<List<Task>> getTaskList(int id)
    {
        return taskRepository.findByAssignedToId(id);
    }

    public Task updateTask(Task task, int id) {
        Task task_det = taskRepository.findById(id).get();
        task_det.setTaskDescription(task.getTaskDescription());
        task_det.setStatus(task.getStatus());
        return taskRepository.save(task_det);
    }
}
