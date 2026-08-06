package com.example.demo.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Task;
import com.example.demo.Service.TaskService;
import com.example.demo.dto.ResponseBody;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/task")
public class TaskController {
    public final TaskService ts;
    private TaskController(TaskService ts){
        this.ts = ts;
    }

    @PostMapping()
    public ResponseBody addTask(@RequestBody Task task){
        return ts.addTask(task);
    }

    @GetMapping()
    public List<ResponseBody> getAllTask(){
        return ts.getAllTask();
    }
    @PutMapping("{id}")
    public ResponseBody updateTask(@PathVariable int id, @RequestBody Task task) {
        return ts.updateTask(id, task);
    }
    @GetMapping("{id}")
    public ResponseBody gettaskById(@PathVariable int id) {
        return ts.getTaskById(id);
    }
    

}