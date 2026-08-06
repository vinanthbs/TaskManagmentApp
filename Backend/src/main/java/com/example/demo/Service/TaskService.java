package com.example.demo.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Entity.Task;
import com.example.demo.Exception.TaskNotFoundException;
import com.example.demo.dto.ResponseBody;
import com.example.demo.Repository.TaskRepo;

@Service
public class TaskService {
    private TaskRepo tr;
    private TaskService(TaskRepo tr){
        this.tr = tr;
    }

    public ResponseBody addTask(Task task){
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        task.setStatus("To Do");
        tr.save(task);
        ResponseBody rb = new ResponseBody();
        rb.setTaskName(task.getTaskName());
        rb.setStatus(task.getStatus());
        rb.setCreatedAt(task.getCreatedAt());
        return rb;
    }

    public List<ResponseBody> getAllTask() {

        List<Task>fetchedTasks =tr.findAll();
        return fetchedTasks.stream()
            .map(task -> {
                ResponseBody rb = new ResponseBody();
                rb.setId(task.getId());
                rb.setTaskName(task.getTaskName());
                rb.setStatus(task.getStatus());
                rb.setCreatedAt(task.getCreatedAt());
                rb.setUpdatedAt(task.getUpdatedAt());
                return rb;
            })
            .toList();
    }

    public ResponseBody updateTask(int id, Task task) {
        Task newTask= tr.findById(id).orElseThrow(()-> new TaskNotFoundException("No Task found with id: "+ id));
        newTask.setStatus(task.getStatus());
        newTask.setTaskName(task.getTaskName());
        newTask.setUpdatedAt(LocalDateTime.now());
        System.out.println("New Task from client is : "+newTask);
        tr.save(newTask);
        ResponseBody rb = new ResponseBody();
        rb.setId(newTask.getId());
        rb.setTaskName(newTask.getTaskName());
        rb.setStatus(newTask.getStatus());
        return rb;    
    }

    public ResponseBody getTaskById(int id) {
        Task t=tr.findById(id).orElseThrow(()-> new TaskNotFoundException("No Task found with id: "+ id));
        ResponseBody rb = new ResponseBody();
        rb.setId(t.getId());
        rb.setTaskName(t.getTaskName());
        rb.setStatus(t.getStatus());
        rb.setCreatedAt(t.getCreatedAt());
        rb.setUpdatedAt(t.getUpdatedAt());
        return rb;
    } 
}
