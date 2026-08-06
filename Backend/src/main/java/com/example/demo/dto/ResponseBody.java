package com.example.demo.dto;

import java.time.LocalDateTime;

public class ResponseBody { 
    private int id;
    private String taskName;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTaskName() {
        return taskName;
    }
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    @Override
    public String toString() {
        return "Task [id=" + id + ", taskName=" + taskName + ", status=" + status + ", createdAt=" + createdAt
                + ", updatedAt=" + updatedAt + "]";
    }   
}