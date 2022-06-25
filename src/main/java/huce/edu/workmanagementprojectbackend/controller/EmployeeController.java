package huce.edu.workmanagementprojectbackend.controller;

import huce.edu.workmanagementprojectbackend.model.EmployeeEntity;
import huce.edu.workmanagementprojectbackend.services.department.IDepartmentService;
import huce.edu.workmanagementprojectbackend.services.employee.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
public class EmployeeController {

  @Autowired
  private IEmployeeService iEmployeeService;

  @Autowired
  private IDepartmentService iDepartmentService;

  @RequestMapping("/employee_manager")
  public String showEmployeeManagerPage(Model model){
    model.addAttribute("employees",iEmployeeService.getAll());
    return "/html/Manager/employee/manager-employee";
  }

  @RequestMapping("/add_employee")
  public String showAddEmployeePage(Model model){
    model.addAttribute("departments",iDepartmentService.getAll());
    return "/html/Manager/employee/manager-employee-add";
  }

  @PostMapping("/save_employee_action")
  public String addEmployee(@ModelAttribute("employee") EmployeeEntity employee){
    if(employee.getId() != 0){
      employee.setUpdateDate(new Date());
      iEmployeeService.updateObject(employee);
    }else{
      employee.setCreateDate(new Date());
      iEmployeeService.insertObject(employee);
    }
    return "redirect:/employee_manager";
  }

  @RequestMapping("/update_employee")
  public String showUpdateEmployeePage(@RequestParam("employeeId")int employeeId,
                                       Model model){
    model.addAttribute("employee",iEmployeeService.getObjectById(employeeId));
    model.addAttribute("departments",iDepartmentService.getAll());
    return "/html/Manager/employee/manager-employee-update";
  }

  @RequestMapping("/delete_employee")
  public String deleteEmployee(@RequestParam("employeeId")int employeeId) {
    iEmployeeService.deleteObject(employeeId);
    return "redirect:/employee_manager";
  }

  @RequestMapping("/employees_of_department")
  public String showEmployeesOfDepartmentPage(@RequestParam("departmentId")int departmentId,
                                              Model model){
    model.addAttribute("employees",iEmployeeService.getObjectsByDepartment(departmentId));
    return "/html/Manager/department/manager-employeeOfDepartment";
  }
}
