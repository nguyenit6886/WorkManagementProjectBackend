package huce.edu.workmanagementprojectbackend.controller;

import huce.edu.workmanagementprojectbackend.model.*;
import huce.edu.workmanagementprojectbackend.paging.Paged;
import huce.edu.workmanagementprojectbackend.services.assignment.IAssignmentService;
import huce.edu.workmanagementprojectbackend.services.employee.IEmployeeService;
import huce.edu.workmanagementprojectbackend.services.task.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class TaskController {

  @Autowired
  private ITaskService iTaskService;

  @Autowired
  private IAssignmentService iAssignmentService;

  @Autowired
  private IEmployeeService iEmployeeService;

  @ResponseBody
  @GetMapping("/tasks")
  public List<TaskEntity> getEmployees(){
    return iTaskService.getAll();
  }

  @ResponseBody
  @GetMapping("/get_assignment")
  public Map<String,Object> getOne(@RequestParam("taskId")int taskId){
    Map<String,Object> map = new LinkedHashMap<>();
    map.put("task",iTaskService.getObjectById(taskId));
    map.put("assignments",iAssignmentService.getAssignmentByTaskActive(iTaskService.getObjectById(taskId)));
    return map;
  }

  @RequestMapping("/employee_task")
  public String showTaskListPage(Model model){
    EmployeeEntity employee = iEmployeeService.getObjectById(LoginController.CREATE_USER_ID);
    model.addAttribute("tasks",iAssignmentService.getTaskByEmployee(employee));
    Map<ProjectEntity,List<TaskEntity>> map = iAssignmentService.getTaskByEmployee(employee)
                                                                .stream().collect(Collectors.groupingBy(w -> w.getProject()));
    return "/html/Employee/employee-task";
  }

  @RequestMapping("/leader-task-list")
  public String showTaskListPage(@RequestParam("projectId") int projectId,
                                 @RequestParam(value = "pageNumber",required = false, defaultValue = "1") int pageNumber,
                                 Model model){
    Paged<TaskEntity> taskEntities = iTaskService.getPageByProjectId(projectId, pageNumber);
    model.addAttribute("tasks", taskEntities);
    model.addAttribute("projectId", projectId);
    model.addAttribute("employees",iEmployeeService.getAll());
    return "/html/Leader/leader-task";
  }

  @ResponseBody
  @RequestMapping("/get_task")
  public TaskEntity getTask(@RequestParam("taskId") int taskId){
    return iTaskService.getObjectById(taskId);
  }

  @RequestMapping("/save_task")
  public String saveTask(@ModelAttribute("task") TaskEntity task,
                         @RequestParam("employees") EmployeeEntity[] employees){
    if(task.getId() != 0){
      iTaskService.updateObject(task);
      List<AssignmentEntity> list = iAssignmentService.getAssignmentByTask(task);
      for(AssignmentEntity ae : list){
        if(!Arrays.asList(employees).contains(ae.getEmployee())){
          if(ae.isActive()) ae.setActive(false);
        }else{
          if(!ae.isActive()) ae.setActive(true);
        }
        ae.setUpdateDate(new Date());
        iAssignmentService.updateObject(ae);
      }
      for(EmployeeEntity employee : employees){
        if(!list.stream().map(AssignmentEntity::getEmployee).collect(Collectors.toList()).contains(employee)){
          addAssignmentByTask(task, employee);
        }
      }
    }else{
      task.setCreateUser(LoginController.CREATE_USER_ID);
      iTaskService.insertObject(task);
      for(EmployeeEntity employee : employees){
        addAssignmentByTask(task, employee);
      }
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

  private void addAssignmentByTask(TaskEntity task, EmployeeEntity employee){
    AssignmentEntity ae = new AssignmentEntity();
    ae.setTask(task);
    ae.setEmployee(employee);
    ae.setCreateDate(new Date());
    ae.setActive(true);
    ae.setCreateUser(LoginController.CREATE_USER_ID);
    iAssignmentService.insertObject(ae);
  }
}
