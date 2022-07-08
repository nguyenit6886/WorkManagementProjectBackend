package huce.edu.workmanagementprojectbackend.controller;

import huce.edu.workmanagementprojectbackend.common.AccountRole;
import huce.edu.workmanagementprojectbackend.model.*;
import huce.edu.workmanagementprojectbackend.paging.Paged;
import huce.edu.workmanagementprojectbackend.services.assignment.IAssignmentService;
import huce.edu.workmanagementprojectbackend.services.employee.IEmployeeService;
import huce.edu.workmanagementprojectbackend.services.project.IProjectService;
import huce.edu.workmanagementprojectbackend.services.task.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class TaskController {

  private List<String> errors = new ArrayList<>();

  @Autowired
  private IProjectService iProjectService;

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
  public String showTaskListPage(Model model,
                                 HttpSession session){
    EmployeeEntity user = (EmployeeEntity) session.getAttribute("user");
    if(user == null){
      return "redirect:/login";
    }
    EmployeeEntity employee = iEmployeeService.getObjectById(user.getId());
    List<TaskEntity> taskEntities = iAssignmentService.getTaskByEmployee(employee);
    Map<ProjectEntity,List<TaskEntity>> map = taskEntities.stream().collect(Collectors.groupingBy(w -> w.getProject()));
    model.addAttribute("tasks",taskEntities);
    model.addAttribute("map",map);
    model.addAttribute("user",user);
    return "/html/Employee/employee-task";
  }

  @RequestMapping("/leader_manager")
  public String showLeaderManagerPage(@RequestParam(value = "pageNumber",required = false, defaultValue = "1") int pageNumber,
                                      Model model,
                                      HttpSession session){
    EmployeeEntity userSession = (EmployeeEntity) session.getAttribute("user");
    if(userSession != null && userSession.getPosition() == AccountRole.ROLE_LEADER.getValue()){
      model.addAttribute("projects",iProjectService.getPageByDepartmentId(pageNumber,((EmployeeEntity) session.getAttribute("user")).getDepartment().getId()));
      model.addAttribute("user",userSession);
      sentError(model);
      return "/html/Leader/leader-project";
    }
    return "redirect:/login";
  }

  @RequestMapping("/leader-task-list")
  public String showTaskListPage(@RequestParam("projectId") int projectId,
                                 @RequestParam(value = "pageNumber",required = false, defaultValue = "1") int pageNumber,
                                 Model model,
                                 HttpSession session){
    Paged<TaskEntity> taskEntities = iTaskService.getPageByProjectId(projectId, pageNumber);
    model.addAttribute("tasks", taskEntities);
    model.addAttribute("projectId", projectId);
    model.addAttribute("employees",iEmployeeService.getAll());
    model.addAttribute("user",session.getAttribute("user"));
    sentError(model);
    return "/html/Leader/leader-task";
  }

  @ResponseBody
  @RequestMapping("/get_task")
  public TaskEntity getTask(@RequestParam("taskId") int taskId){
    return iTaskService.getObjectById(taskId);
  }

  @RequestMapping("/save_task")
  public String saveTask(@ModelAttribute("task") TaskEntity task,
                         @RequestParam(value = "employees",required = false) EmployeeEntity[] employees,
                         HttpSession session){
    if(validateSave(task)){
      if(task.getId() != 0){
        iTaskService.updateObject(task);
        if(employees != null){
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
        }
      }else{
        EmployeeEntity employeeEntity = (EmployeeEntity) session.getAttribute("user");
        if(employeeEntity == null || employeeEntity.getPosition() != AccountRole.ROLE_LEADER.getValue()){
          return "redirect:/login";
        }
        task.setCreateUser(employeeEntity.getId());
        iTaskService.insertObject(task);
        if(employees != null){
          for(EmployeeEntity employee : employees){
            addAssignmentByTask(task, employee);
          }
        }
      }
    }
    String url = "/leader-task-list?projectId="+task.getProject().getId();
    return "redirect:"+url;
  }

  @RequestMapping("/delete_task")
  public String deleteTask(@RequestParam("taskId") int taskId){
    if(validateDelete(taskId)){
      iTaskService.deleteObject(taskId);
      TaskEntity task = iTaskService.getObjectById(taskId);
      String url = "/leader-task-list?projectId="+task.getProject().getId();
      return "redirect:"+url;
    }
    return "redirect:/leader_manager";
  }

  @RequestMapping("/manager-task-list")
  public String showTaskListManagerPage(@RequestParam("projectId") int projectId,
                                        @RequestParam(value = "pageNumber",required = false, defaultValue = "1") int pageNumber,
                                        Model model,
                                        HttpSession session){
    //List<TaskEntity> taskEntities = iTaskService.getAllTasksByProjectId(projectId);
    model.addAttribute("tasks", iTaskService.getPageByProjectId(projectId, pageNumber));
    model.addAttribute("projectId", projectId);
    model.addAttribute("user",session.getAttribute("user"));
    return "/html/Manager/project/manager-task";
  }

  private void addAssignmentByTask(TaskEntity task,
                                   EmployeeEntity employee){
    AssignmentEntity ae = new AssignmentEntity();
    ae.setTask(task);
    ae.setEmployee(employee);
    ae.setCreateDate(new Date());
    ae.setActive(true);
    ae.setCreateUser(employee.getId());
    iAssignmentService.insertObject(ae);
  }

  private boolean validateSave(TaskEntity taskEntity){
    boolean valid = true;
    if(taskEntity.getTitle().isEmpty()){
      errors.add("Chưa nhập tên công việc");
      return false;
    }
    if(taskEntity.getBeginDate() != null){
      if(taskEntity.getDeadline() != null && taskEntity.getBeginDate().isAfter(taskEntity.getDeadline())){
        errors.add("Ngày bắt đầu không thể lớn hơn thời hạn");
        valid = false;
      }
      if(taskEntity.getEndDate() != null && taskEntity.getBeginDate().isAfter(taskEntity.getEndDate())){
        errors.add("Ngày bắt đầu không thể lớn hơn ngày kết thúc");
        valid = false;
      }
    }
    return valid;
  }

  private boolean validateDelete(int taskId){
    if(iTaskService.getAll().stream().map(TaskEntity::getId).collect(Collectors.toList()).contains(taskId)){
      return true;
    }else{
      errors.add("Công việc không tồn tại");
      return false;
    }
  }

  private void sentError(Model model){
    if(errors.size() > 0){
      StringBuilder string = new StringBuilder();
      for(String error : errors){
        string.append(error);
        string.append('\n');
      }
      model.addAttribute("error",string);
      errors.clear();
    }
  }
}
