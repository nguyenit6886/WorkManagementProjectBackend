package huce.edu.workmanagementprojectbackend.controller;

import huce.edu.workmanagementprojectbackend.model.EmployeeEntity;
import huce.edu.workmanagementprojectbackend.services.department.IDepartmentService;
import huce.edu.workmanagementprojectbackend.services.employee.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jws.WebParam;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

  @Autowired
  private IEmployeeService iEmployeeService;

  @Autowired
  private IDepartmentService iDepartmentService;

  @RequestMapping("/login")
  public String showLogin(){
    return "/html/login";
  }

  @RequestMapping("/manager")
  public String showManagerPage(){
    return "/html/Manager/manager-index";
  }

  @PostMapping("/checklogin")
  public String checkLogin(@RequestParam("username")String username,
                           @RequestParam("password")String password,
                           Model model,
                           HttpSession session){
    EmployeeEntity employee = iEmployeeService.getEmployeeForLogin(username,password);
    if(employee != null){
      session.setAttribute("user",employee);
      switch (employee.getPosition()){
        case 0:
          return "redirect:/project_manager";
        case 1:
          return "redirect:/leader_manager";
        case 2:
          return "redirect:/tasks";
      }
    }else{
      model.addAttribute("notify","Have not account");
      return "/html/login";
    }
    return "/html/login";
  }
}
