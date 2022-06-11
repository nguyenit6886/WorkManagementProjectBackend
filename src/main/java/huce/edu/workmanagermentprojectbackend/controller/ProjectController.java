package huce.edu.workmanagermentprojectbackend.controller;

import huce.edu.workmanagermentprojectbackend.model.ProjectEntity;
import huce.edu.workmanagermentprojectbackend.services.project.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ProjectController {

  @Autowired
  private IProjectService iProjectService;

  @ResponseBody
  @GetMapping("/projects")
  public List<ProjectEntity> getDepartments(){
    return iProjectService.getAll();
  }

  @ResponseBody
  @GetMapping("/project_by_id")
  public ProjectEntity getDepartmentById(@RequestParam int projectId){
    return iProjectService.getObjectById(projectId);
  }
}
