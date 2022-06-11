package huce.edu.workmanagementprojectbackend.controller;

import huce.edu.workmanagementprojectbackend.model.TaskEntity;
import huce.edu.workmanagementprojectbackend.services.task.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

public class TaskController {

  @Autowired
  private ITaskService iTaskService;

  @ResponseBody
  @GetMapping("/tasks")
  public List<TaskEntity> getEmployees(){
    return iTaskService.getAll();
  }

}