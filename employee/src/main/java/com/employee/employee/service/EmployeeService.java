package com.employee.employee.service;
import com.employee.employee.entity.Employee;
import com.employee.employee.entity.Task;
import com.employee.employee.repository.EmployeeRepository;
// import com.employee.employee.repository.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
// import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private final EmployeeRepository employeeRepository;

    @Autowired
    TaskService taskService;

    // @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(int id) {
        return employeeRepository.findById(id).get();
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public String deleteEmployee(int id) 
    {        
        String emp_name = getEmployeeById(id).getName();        
        List<Task> task_list= taskService.getTaskList(id);
        for (Task task : task_list)
        {
            task.setAssignedTo(null);
        }
        employeeRepository.deleteById(id);
        return String.format("%s : employee deleted succfully..!", emp_name);
    }

    public List<Employee> saveEmployees(List<Employee> employee_list) {
        
        return employeeRepository.saveAll(employee_list);
    }

    public Employee updateEmployee(Employee employee, int id) {
        Employee employee_det = employeeRepository.findById(id).get();
        employee_det.setName(employee.getName());
        employee_det.setDept(employee.getDept());
        Employee emp =employeeRepository.save(employee_det);
        return emp;
    }
}

