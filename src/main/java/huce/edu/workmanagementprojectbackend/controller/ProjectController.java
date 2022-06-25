package huce.edu.workmanagementprojectbackend.controller;

import huce.edu.workmanagementprojectbackend.model.ProjectEntity;
import huce.edu.workmanagementprojectbackend.services.project.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ProjectController {

  @Autowired
  private IProjectService iProjectService;

  @RequestMapping("/project_manager")
  public String showProjectManagerPage(Model model){
    model.addAttribute("projects",iProjectService.getAll());
    return "/html/Manager/project/manager-project";
  }
}
