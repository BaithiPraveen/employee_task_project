package com.employee.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.employee.entity.User;


public interface UserRepository extends JpaRepository<User,Integer>
{
}
