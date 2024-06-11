package com.employee.employee.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.employee.entity.Employee;
// import com.employee.employee.exceptions.CustomExceptions;
import com.employee.employee.service.EmployeeService;

import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<?> getAllEmployees() {
        // System.out.println("iam in controller");
        try {
            List<Employee> employee_list = employeeService.getAllEmployees();
            if (employee_list.isEmpty())
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No records found ..!");
            else
                return new ResponseEntity<>(employee_list, HttpStatus.OK);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
            // return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable int id) {
        try {
            if (id < 1) {
                // System.out.print("entrerd into exception");
                // throw new CustomExceptions("Invalid input");
                return ResponseEntity.badRequest().body("Invalid input data..!");
                // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid input
                // data..!");
            }

            else {
                // System.out.println("employee data : ");
                // System.out.println(employeeService.getEmployeeById(id).get());
                Optional<Employee> employee = employeeService.getEmployeeById(id);
                // System.out.println("employee data : "+employee);
                if (employee.isPresent())
                    return new ResponseEntity<>(employee.get(), HttpStatus.OK);
                else
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("employee not found");
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }
    }

    @PostMapping
    public ResponseEntity<?> saveEmployee(@Valid @RequestBody Employee employee) {
        try {
            if (employee.getName() == null)
                return ResponseEntity.badRequest().body("Invalid input employee data..! missing Name field");
            Employee employee_data = employeeService.saveEmployee(employee);
            if (employee_data != null)
                return new ResponseEntity<>(employee_data, HttpStatus.CREATED);
            else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/save_emp_list")
    public ResponseEntity<?> saveEmployees(@RequestBody List<Employee> employee_list) {
        try {
            if (employee_list.isEmpty())
                return ResponseEntity.badRequest().body("list is empty...!");
            List<Employee> employee_list_data = employeeService.saveEmployees(employee_list);
            return new ResponseEntity<>(employee_list_data, HttpStatus.CREATED);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@RequestBody Employee employee, @PathVariable int id) {
        try {
            if (employee != null && id > 0) {
                Employee updatedEmployee = employeeService.updateEmployee(employee, id);
                if (updatedEmployee != null)
                    return ResponseEntity.ok(updatedEmployee);
                else
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("employee not found ..!");
            } else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid input data..!");
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int id) {
        try {
            if (id < 1)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid input data..!");
            else {
                String msg = employeeService.deleteEmployee(id);
                if (msg != null)
                    return ResponseEntity.ok(msg);
                else
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("employee not found ...!");
            }
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getNamesEmployees(@PathVariable("name") String name) {
        try {
            if (name != null && name.length() >= 1)
                return ResponseEntity.ok(employeeService.getNamesEmployees(name));
            else
                return ResponseEntity.badRequest().body("input name is not valid..! ");
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

    }

    @GetMapping("/dept/{dept}")
    public ResponseEntity<?> getDeptEmployees(@PathVariable("dept") String dept) {
        try {
            if (dept != null && dept.length() >= 1)
                return ResponseEntity.ok(employeeService.getDeptEmployees(dept));
            else
                return ResponseEntity.badRequest().body("input dept not valid..!");
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());

        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getDeptEmployees(@RequestParam("name") String name, @RequestParam("dept") String dept) {
        try {
            if (name.isEmpty() || name.isBlank() || dept.isEmpty() || dept.isBlank())
                return ResponseEntity.badRequest().body("input parameters or not valid ..!");
            else
                return ResponseEntity.ok(employeeService.getByNameAndDept(name, dept));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());

        }
    }
}
