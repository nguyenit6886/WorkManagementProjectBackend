package huce.edu.workmanagementprojectbackend.controller;

import huce.edu.workmanagementprojectbackend.model.EmployeeEntity;
import huce.edu.workmanagementprojectbackend.services.department.IDepartmentService;
import huce.edu.workmanagementprojectbackend.services.employee.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

  @Autowired
  private IEmployeeService iEmployeeService;

  @Autowired
  private IDepartmentService iDepartmentService;

  @RequestMapping("/login")
  public String showLogin(Model model,
                          @RequestParam(required = false) String error){
    model.addAttribute("error", error);
    return "/html/login";
  }

  @RequestMapping("/manager")
  public String showManagerPage(){
    return "/html/Manager/manager-index";
  }

}
