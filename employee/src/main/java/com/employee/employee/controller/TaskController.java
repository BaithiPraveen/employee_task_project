package com.employee.employee.controller;

import java.util.List;
// import java.util.Optional;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.employee.entity.Task;
import com.employee.employee.service.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController
{
    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<?> getAllTasks()
    {
        try
        {
            List<Task> list_task =  taskService.getAllTasks();
            if (list_task.isEmpty())
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no records found in tasks..!");
            else
                return ResponseEntity.ok(list_task);
        }
        catch(Exception ex)
        {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable int id)
    {
        try
        {
            if (id<1)
                return ResponseEntity.badRequest().body("invalid input task id..!");
            Optional<Task> task_data = taskService.getTaskById(id);
            System.out.println("task data : "+task_data);
            if (task_data.isPresent())
                return ResponseEntity.ok(task_data);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("task not found...!");
        }
        catch(Exception ex)
        {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<String> saveTask(@RequestBody Task task)
    {
        try
        {
            if (task.getTaskDescription() == null)
                return ResponseEntity.badRequest().body("invalid input data..!");
            Task task_data = taskService.saveTask(task);
            if (task_data == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("assignedTo_id is not found in employee table..!");
            else
                return new ResponseEntity<>(String.format("%s task is created succefully",task_data.getTaskDescription()),HttpStatus.CREATED);
        }
        catch(Exception ex)
        {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable int id,@RequestBody Task task)
    {
        try
        {
            if (id<1 || task.getTaskDescription() == null)
                return ResponseEntity.badRequest().body("invalid input data...!");
            Task task_data = taskService.updateTask(task,id);
            if (task_data == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("invalid data..!");
            return ResponseEntity.ok(task_data);
        }
        catch(Exception ex)
        {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>  deleteTask(@PathVariable int id) 
    {
        try
        {
            if (id<1)
                return ResponseEntity.badRequest().body("invalid input data..!");
            String delete_msg = taskService.deleteTask(id);
                return ResponseEntity.ok(delete_msg);
        }
        catch(Exception ex)
        {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/list/{eid}")
    public ResponseEntity<?> getTaskList(@PathVariable("eid") int eid)
    {
        try
        {
            if (eid<1)
                return ResponseEntity.badRequest().body("invalid input data..!");
            List<Task> task_data =taskService.getTaskList(eid).get();
            if (task_data.isEmpty())
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no records found found..!");
            return ResponseEntity.ok(task_data);
        }
        catch (Exception ex)
        {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

}
