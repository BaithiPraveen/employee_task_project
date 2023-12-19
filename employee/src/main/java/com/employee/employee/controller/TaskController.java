package com.employee.employee.controller;

import java.util.List;
// import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
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
    public ResponseEntity<List<Task>> getAllTasks()
    {
        List<Task> list_task =  taskService.getAllTasks();
        try
        {
            if (list_task.isEmpty())
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            else
            {
                return ResponseEntity.ok(list_task);
            }
            
        }
        catch(Exception ex)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable int id)
    {
        try
        {
            if (id<1)
                return ResponseEntity.badRequest().build();
            Task task_data = taskService.getTaskById(id);
            if (task_data == null)
                return ResponseEntity.notFound().build();
            else
                return ResponseEntity.ok(task_data);

        }
        catch(Exception ex)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<String> saveTask(@RequestBody Task task)
    {
        try
        {
            if (task == null)
                return ResponseEntity.badRequest().build();
            Task task_data = taskService.saveTask(task);
            if (task_data == null)
                return ResponseEntity.notFound().build();
            else
                return new ResponseEntity<>(String.format("%s task is created succfully",task_data.getTaskDescription()),HttpStatus.CREATED);
        }
        catch(Exception ex)
        {
            return ResponseEntity.badRequest().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable int id,@RequestBody Task task)
    {
        try
        {
            if (id<1 || task == null)
                return ResponseEntity.badRequest().build();
            Task task_data = taskService.updateTask(task,id);
            return ResponseEntity.ok(task_data);
        }
        catch(Exception ex)
        {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>  deleteTask(@PathVariable int id) 
    {
        try 
        {
            if (id<1)
                return ResponseEntity.badRequest().build();
            String delete_msg = taskService.deleteTask(id);
            if (delete_msg.contains("Task is deleted succfully..!"))
                return ResponseEntity.ok(delete_msg);
            else
                return ResponseEntity.badRequest().build();
        }
        catch(Exception ex)
        {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/list/{eid}")
    public ResponseEntity<List<Task>> getTaskList(@PathVariable("eid") int eid)
    {
        try
        {
            if (eid<1)
                return ResponseEntity.badRequest().build();
            Optional<List<Task>> task_list = taskService.getTaskList(eid);
            System.out.println("task list : "+task_list);
            List<Task> task_data = task_list.get();
            if (! task_data.isEmpty())
            {
                return ResponseEntity.ok(task_data);
            }
            else return ResponseEntity.notFound().build();

        }
        catch (Exception ex)
        {
            return ResponseEntity.badRequest().build();
        }
    }

}
