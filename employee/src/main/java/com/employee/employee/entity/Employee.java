package com.employee.employee.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee_data",
    uniqueConstraints =
    {
        @UniqueConstraint(columnNames = {"email"})
    }
)
public class Employee extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotEmpty(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotEmpty(message = "Department is required")
    @NotNull(message = "Department is required due to not null") //missing field
    @Size(min = 2, max = 50, message = "Department must be between 2 and 50 characters")
    private String dept;

    @NotEmpty(message = "email is required") 
    @NotNull(message = "email is required due to not null")
    @Email(message = "email is not valid..!")
    private String email;
}
