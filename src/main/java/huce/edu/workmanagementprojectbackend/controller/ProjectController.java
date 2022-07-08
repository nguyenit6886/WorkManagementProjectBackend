package huce.edu.workmanagementprojectbackend.controller;

import huce.edu.workmanagementprojectbackend.common.AccountRole;
import huce.edu.workmanagementprojectbackend.model.AssignmentDepartmentEntity;
import huce.edu.workmanagementprojectbackend.model.DepartmentEntity;
import huce.edu.workmanagementprojectbackend.model.EmployeeEntity;
import huce.edu.workmanagementprojectbackend.model.ProjectEntity;
import huce.edu.workmanagementprojectbackend.services.assignmentdepartment.IAssignmentDepartmentService;
import huce.edu.workmanagementprojectbackend.services.department.IDepartmentService;
import huce.edu.workmanagementprojectbackend.services.project.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class ProjectController {

  @Autowired
  private IProjectService iProjectService;

  @Autowired
  private IDepartmentService iDepartmentService;

  @Autowired
  private IAssignmentDepartmentService iAssignmentDepartmentService;

  @ResponseBody
  @GetMapping("/get_project")
  public Map<String,Object> getOne(@RequestParam("projectId")int projectId){
    Map<String,Object> map = new LinkedHashMap<>();
    map.put("project",iProjectService.getObjectById(projectId));
    map.put("assignments",iAssignmentDepartmentService.getAssignmentDepartmentByProjectActive(iProjectService.getObjectById(projectId)));
    return map;
  }

  @RequestMapping("/project_manager")
  public String showProjectManagerPage(@RequestParam(value = "pageNumber",required = false, defaultValue = "1") int pageNumber,
                                       Model model,
                                       HttpSession session){
    model.addAttribute("projects",iProjectService.getPage(pageNumber));
    model.addAttribute("departments",iDepartmentService.getAll());
    EmployeeEntity userSession = (EmployeeEntity) session.getAttribute("user");
    model.addAttribute("user",userSession);
    if(userSession != null && userSession.getPosition() == AccountRole.ROLE_MANAGER.getValue()){
      model.addAttribute("user",userSession);
      return "/html/Manager/project/manager-project";
    }
    return "redirect:/login";
  }

  @RequestMapping("/leader_manager")
  public String showLeaderManagerPage(@RequestParam(value = "pageNumber",required = false, defaultValue = "1") int pageNumber,
                                      Model model,
                                      HttpSession session){
    model.addAttribute("projects",iProjectService.getPageByDepartmentId(pageNumber,((EmployeeEntity) session.getAttribute("user")).getDepartment().getId()));
    EmployeeEntity userSession = (EmployeeEntity) session.getAttribute("user");
    if(userSession != null && userSession.getPosition() == AccountRole.ROLE_LEADER.getValue()){
      model.addAttribute("user",userSession);
      return "/html/Leader/leader-project";
    }
    return "redirect:/login";
  }

  @RequestMapping("/save_project")
  public String addProject(@ModelAttribute("project") ProjectEntity project,
                           @RequestParam(value = "departments",required = false) DepartmentEntity[] departments,
                           HttpSession session){
    EmployeeEntity employee = (EmployeeEntity) session.getAttribute("user");
    if(project.getId() != 0){
      project.setUpdateDate(new Date());
      iProjectService.updateObject(project);
      List<AssignmentDepartmentEntity> list = iAssignmentDepartmentService.getAssignmentDepartmentByProject(project);
      for(AssignmentDepartmentEntity ade : list){
        if(!Arrays.asList(departments).contains(ade.getDepartment())){
          if(ade.isActive()) ade.setActive(false);
        }else{
          if(!ade.isActive()) ade.setActive(true);
        }
        ade.setUpdateDate(new Date());
        iAssignmentDepartmentService.updateObject(ade);
      }
      for(DepartmentEntity department : departments){
        if(!list.stream().map(AssignmentDepartmentEntity::getDepartment).collect(Collectors.toList()).contains(department)){
          addAssignmentDepartmentByProject(project, department, session);
        }
      }
    }else{
      project.setCreateDate(new Date());
      project.setActive(true);
      project.setCreateUser(employee.getId());
      iProjectService.insertObject(project);
      for(DepartmentEntity department : departments){
        addAssignmentDepartmentByProject(project, department, session);
      }
    }
    return "redirect:/project_manager";
  }

  @RequestMapping("/delete_project")
  public String deleteProject(@RequestParam("projectId")int projectId) {
    iProjectService.deleteObject(projectId);
    return "redirect:/project_manager";
  }

  private void addAssignmentDepartmentByProject(ProjectEntity project,
                                                DepartmentEntity department,
                                                HttpSession session){
    AssignmentDepartmentEntity ade = new AssignmentDepartmentEntity();
    EmployeeEntity employee = (EmployeeEntity) session.getAttribute("user");
    ade.setProject(project);
    ade.setDepartment(department);
    ade.setCreateDate(new Date());
    ade.setActive(true);
    ade.setCreateUser(employee.getId());
    iAssignmentDepartmentService.insertObject(ade);
  }
}
