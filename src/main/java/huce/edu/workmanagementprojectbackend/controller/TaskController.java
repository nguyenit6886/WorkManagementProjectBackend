package huce.edu.workmanagementprojectbackend.controller;

import huce.edu.workmanagementprojectbackend.model.ProjectEntity;
import huce.edu.workmanagementprojectbackend.model.TaskEntity;
import huce.edu.workmanagementprojectbackend.services.assignment.IAssignmentService;
import huce.edu.workmanagementprojectbackend.services.project.IProjectService;
import huce.edu.workmanagementprojectbackend.services.task.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class TaskController {

  @Autowired
  private ITaskService iTaskService;

  @ResponseBody
  @GetMapping("/tasks")
  public List<TaskEntity> getEmployees(){
    return iTaskService.getAll();
  }

  @RequestMapping("/tasklist")
  public String showTaskListPage(Model model){
    model.addAttribute("tasks",iTaskService.getAll());
    return "/html/Employee/employee-task";
  }

  @RequestMapping("/leader-task-list")
  public String showTaskListPage(@RequestParam("projectId") int projectId, Model model){
    List<TaskEntity> taskEntities = iTaskService.getAllTasksByProjectId(projectId);
    model.addAttribute("tasks", taskEntities);
    model.addAttribute("projectId", projectId);
    return "/html/Leader/leader-task";
  }
  @ResponseBody
  @RequestMapping("/get_task")
  public TaskEntity getTask(@RequestParam("taskId") int taskId){
    return iTaskService.getObjectById(taskId);
  }

  @RequestMapping("/save_task")
  public String saveTask(@ModelAttribute("task") TaskEntity task){
    if(task.getId() != 0){
      iTaskService.updateObject(task);
    }else{
      iTaskService.insertObject(task);
    }
    String url = "/leader-task-list?projectId="+task.getProject().getId();
    return "redirect:"+url;
  }

  @RequestMapping("/delete_task")
  public String deleteTask(@RequestParam("taskId") int taskId){
    iTaskService.deleteObject(taskId);
    TaskEntity task = iTaskService.getObjectById(taskId);
    String url = "/leader-task-list?projectId="+task.getProject().getId();
    return "redirect:"+url;
  }

}
