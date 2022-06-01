package com.niit.taskservice.controller;


import com.niit.taskservice.model.Task;
import com.niit.taskservice.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/myapp/v1")
public class TaskController {

    private TaskService taskService;

    @Autowired

    public TaskController(TaskService taskService) {
        this.taskService=taskService;
    }


//    {
//        "taskid":1,
//            "userid":2,
//            "title":"Gym",
//            "status":true,
//            "date":"2012-05-12",
//            "priority":"medium",
//            "image":"yfyuuygiugiuiuhkkk"
//
//    }

    @GetMapping("/task")
    public ResponseEntity<?> getAllTasks()
    {
        return  new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
    }

    @PostMapping("/task")
    public ResponseEntity<?> addTask(@RequestBody Task task)
    {
        return  new ResponseEntity<>(taskService.addTask(task),HttpStatus.OK);
    }

    @GetMapping("/task-by-userid/{userid}")
    public ResponseEntity<?> getTaskByTaskId(@PathVariable("userid") int userid)
    {
        return new ResponseEntity<>(taskService.getTaskByUserId(userid),HttpStatus.OK);
    }

    @PutMapping("/task")
    public ResponseEntity<?> updateTask(@RequestBody Task task)
    {
        return  new ResponseEntity<>(taskService.updateTask(task),HttpStatus.OK);
    }

    @DeleteMapping("/task/{taskid}")
    public  ResponseEntity<?> deleteTaskByTaskId(@PathVariable int taskid)
    {
        taskService.deleteTask(taskid);
        return new ResponseEntity<>("Deleted the Task",HttpStatus.OK);
    }

}
