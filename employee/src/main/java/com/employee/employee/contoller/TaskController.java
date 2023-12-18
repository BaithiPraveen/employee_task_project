package com.employee.employee.contoller;

import java.util.List;
// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


import com.employee.employee.entity.Task;
import com.employee.employee.service.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController 
{
    private final TaskService taskService;

    // @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable int id) {
        return taskService.getTaskById(id);
    }

    @PostMapping
    public Task saveTask(@RequestBody Task task) {
        return taskService.saveTask(task);
    }
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable int id,@RequestBody Task task)
    {
        return taskService.updateTask(task,id);
    }

    @DeleteMapping("/{id}")
    public String  deleteTask(@PathVariable int id) {
        return taskService.deleteTask(id);
    }

    @GetMapping("/tasks_list/{eid}")
    public List<Task> getTaskList(@PathVariable("eid") int eid)
    {
        return taskService.getTaskList(eid);
    }

}
