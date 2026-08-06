package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Task;

public interface TaskRepo extends JpaRepository <Task, Integer>{
    
}
