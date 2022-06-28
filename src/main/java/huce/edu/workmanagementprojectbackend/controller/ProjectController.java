package huce.edu.workmanagementprojectbackend.controller;

import huce.edu.workmanagementprojectbackend.model.DepartmentEntity;
import huce.edu.workmanagementprojectbackend.model.ProjectEntity;
import huce.edu.workmanagementprojectbackend.services.department.IDepartmentService;
import huce.edu.workmanagementprojectbackend.services.project.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class ProjectController {

  @Autowired
  private IProjectService iProjectService;

  @Autowired
  private IDepartmentService iDepartmentService;

  @ResponseBody
  @GetMapping("/getone_project")
  public ProjectEntity getOne(@RequestParam("projectId")int projectId){
    return iProjectService.getObjectById(projectId);
  }

  @RequestMapping("/project_manager")
  public String showProjectManagerPage(Model model){
    model.addAttribute("projects",iProjectService.getAll());
    model.addAttribute("departments",iDepartmentService.getAll());
    return "/html/Manager/project/manager-project";
  }

  @RequestMapping("/save_project")
  public String addProject(@ModelAttribute("project") ProjectEntity project,
                           @RequestParam("departments") DepartmentEntity[] departments){
    if(project.getId() != 0){
      project.setUpdateDate(new Date());
      iProjectService.updateObject(project);
    }else{
      project.setCreateDate(new Date());
      project.setActive(true);
      project.setCreateUser(LoginController.CREATE_USER_ID);
      iProjectService.insertObject(project);
      //Todo: Add assignment department
//      for(DepartmentEntity department : departments){
//
//      }
    }
    return "redirect:/project_manager";
  }

  @RequestMapping("/delete_project")
  public String deleteEmployee(@RequestParam("projectId")int projectId) {
    iProjectService.deleteObject(projectId);
    return "redirect:/project_manager";
  }
}
