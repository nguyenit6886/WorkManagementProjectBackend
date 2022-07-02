package huce.edu.workmanagementprojectbackend.controller;

import huce.edu.workmanagementprojectbackend.model.TaskEntity;
import huce.edu.workmanagementprojectbackend.services.task.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

  @RequestMapping("/manager-task-list")
  public String showTaskListManagerPage(@RequestParam("projectId") int projectId,
                                        @RequestParam(value = "pageNumber",required = false, defaultValue = "1") int pageNumber,
                                        Model model){
    //List<TaskEntity> taskEntities = iTaskService.getAllTasksByProjectId(projectId);
    model.addAttribute("tasks", iTaskService.getPageByProjectId(projectId, pageNumber));
    model.addAttribute("projectId", projectId);
    return "/html/Manager/project/manager-task";
  }
}
