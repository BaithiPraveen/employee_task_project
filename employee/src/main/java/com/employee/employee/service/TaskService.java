package com.employee.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// import com.employee.employee.repository.EmployeeRepository;

// import org.springframework.beans.factory.annotation.Autowired;

import com.employee.employee.repository.TaskRepository;
import com.employee.employee.entity.Employee;
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

    public Task getTaskById(int id) {
        return taskRepository.findById(id).get();
    }

    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    public String deleteTask(int id) {
        String task_desc = getTaskById(id).getTaskDescription();
        taskRepository.deleteById(id);
        return String.format("%s : Task is deleted succfully..!",task_desc);
    }

    public List<Task> getTaskList(int id)
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
