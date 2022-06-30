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

@Controller
public class LoginController {

  public static int CREATE_USER_ID = 3;

  @Autowired
  private IEmployeeService iEmployeeService;

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
                           Model model){
    EmployeeEntity employee = iEmployeeService.getObjectForLogin(username);
    if(employee == null){
      model.addAttribute("notify","Have not account");
      return "/html/login";
    }else if(employee.getPasswordHash().equals(password)){
      CREATE_USER_ID = employee.getId();
      return "redirect:/manager";
    }else{
      return "/html/login";
    }
  }
}
