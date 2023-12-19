package com.employee.employee.entity;

// import jakarta.persistence.CascadeType;
// import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tasks_p")
public class Task 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String taskDescription;

    private String status;

    // @ManyToOne(cascade = CascadeType.REMOVE)
    @ManyToOne
    @JoinColumn(name = "assigned_to_id", nullable = true)
    private Employee assignedTo;//findByAssignedToId

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Employee getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Employee assignedTo) {
        this.assignedTo = assignedTo;
    }



}
