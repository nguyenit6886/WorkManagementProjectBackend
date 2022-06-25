package huce.edu.workmanagementprojectbackend.controller;

import huce.edu.workmanagementprojectbackend.services.department.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DepartmentManagerController {

  @Autowired
  private IDepartmentService iDepartmentService;

  @RequestMapping("/department_manager")
  public String showLogin(Model model){
    model.addAttribute("departments",iDepartmentService.getAll());
    return "/html/Manager/department/manager-department";
  }
}
