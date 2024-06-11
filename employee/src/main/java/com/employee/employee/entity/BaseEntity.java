package com.employee.employee.entity;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class BaseEntity {

    @JsonIgnore
    @CreationTimestamp
    @Column(name = "created_date")
    private Date createdDate;

    @JsonIgnore
    @UpdateTimestamp
    @Column(name = "updated_date")
    private Date updatedDate;

}
