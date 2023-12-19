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
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TaskService taskService;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(int id) {
        return employeeRepository.findById(id);
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public String deleteEmployee(int id)
    {
        Optional<Employee>emp_obj= getEmployeeById(id);
        if(emp_obj.isPresent())
        {
            String emp_name = emp_obj.get().getName();
            List<Task> task_list= taskService.getTaskList(id).get();

            for (Task task : task_list)
                task.setAssignedTo(null);

            employeeRepository.deleteById(id);
            return String.format("%s : employee deleted succfully..!", emp_name);
        }
        else
            return null;
    }

    public List<Employee> saveEmployees(List<Employee> employee_list) {
        
        return employeeRepository.saveAll(employee_list);
    }

    public Employee updateEmployee(Employee employee, int id) {
        Optional<Employee> employee_det = employeeRepository.findById(id);
        if (employee_det.isPresent())
        {
            Employee employee_data_obj = employee_det.get();
            employee_data_obj.setName(employee.getName());
            employee_data_obj.setDept(employee.getDept());
            Employee emp =employeeRepository.save(employee_data_obj);
            return emp;
        }
        else
            return null;
    }

    public List<Employee> getNamesEmployees(String name)
    {
        return employeeRepository.findByName(name);
    }

    public List<Employee> getDeptEmployees(String dept)
    {
        return employeeRepository.findByDept(dept);
    }

    public List<Employee> getByNameAndDept(String name,String dept)
    {
        return employeeRepository.findByNameAndDept(name,dept);
    }
}

