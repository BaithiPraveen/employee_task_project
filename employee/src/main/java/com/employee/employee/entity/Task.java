package com.employee.employee.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tasks_data")
public class Task extends BaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotEmpty(message = "Task description is required")
    @Size(min = 5, max = 255, message = "Task description must be between 5 and 255 characters")
    private String taskDescription;

    @NotEmpty(message = "Status is required")
    private String status;

    // @ManyToOne(cascade = CascadeType.REMOVE)
    @ManyToOne
    @JoinColumn(name = "assigned_to_id", nullable = true)
    private Employee assignedTo;//findByAssignedToId
}
