package com.employee.employee.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.employee.entity.Employee;
import com.employee.employee.exceptions.CustomExceptions;
import com.employee.employee.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController 
{
    @Autowired
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee>employee_list = employeeService.getAllEmployees();
        if (employee_list.size()>0)
            return new ResponseEntity<>(employee_list,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) throws CustomExceptions
    {
        try
        {
            if (id<1){
                // System.out.print("entrerd into exception");
                // throw new CustomExceptions("Invalid input");
                return ResponseEntity.badRequest().build();
            }

            else
            {
                // System.out.println("employee data : ");
                // System.out.println(employeeService.getEmployeeById(id).get());
                Optional<Employee> employee = employeeService.getEmployeeById(id);
                // System.out.println("employee data : "+employee);
                if (employee.isPresent())
                    return new ResponseEntity<>(employee.get(),HttpStatus.OK);
                else
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }
        catch(Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }
    }

    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) throws CustomExceptions 
    {
        try
        {
            if (employee == null)
                return ResponseEntity.badRequest().build();
            Employee employee_data = employeeService.saveEmployee(employee);
            if (employee_data != null)
                return new ResponseEntity<>(employee_data,HttpStatus.CREATED);
            else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        catch(Exception ex)
        {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/save_emp_list")
    public ResponseEntity<List<Employee>> saveEmployees(@RequestBody List<Employee> employee_list) throws CustomExceptions 
    {
        try
        {
            if (employee_list.isEmpty())
                return ResponseEntity.badRequest().build();
            List<Employee> employee_list_data = employeeService.saveEmployees(employee_list);
            return new ResponseEntity<>(employee_list_data,HttpStatus.CREATED);

        }
        catch(Exception ex)
        {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee,@PathVariable int id)
    {
        try {
            if (employee != null && id > 0)
            {
                Employee updatedEmployee = employeeService.updateEmployee(employee, id);
                if (updatedEmployee != null)
                    return ResponseEntity.ok(updatedEmployee);
                else
                    return ResponseEntity.notFound().build();
            }
            else
                return ResponseEntity.badRequest().build();
        }
        catch (Exception ex)
        {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int id)
    {
        try
        {
            if (id<1)
                return ResponseEntity.badRequest().build();
            else
            {
                String msg = employeeService.deleteEmployee(id);
                if (msg != null)
                    return ResponseEntity.ok(msg);
                else
                    return ResponseEntity.notFound().build();
            }
        }
        catch(Exception ex){
            return ResponseEntity.badRequest().build();
        }
    }
}
