package huce.edu.workmanagementprojectbackend.controller;

import huce.edu.workmanagementprojectbackend.model.EmployeeEntity;
import huce.edu.workmanagementprojectbackend.services.department.IDepartmentService;
import huce.edu.workmanagementprojectbackend.services.employee.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class EmployeeController {

  @Autowired
  private IEmployeeService iEmployeeService;

  @Autowired
  private IDepartmentService iDepartmentService;

  @ResponseBody
  @GetMapping("/employees")
  public List<EmployeeEntity> getEmployees(){
    return iEmployeeService.getAll();
  }

  @ResponseBody
  @GetMapping("/employee_by_department_id")
  public List<EmployeeEntity> getEmployeesByDepartmentId(@RequestParam int departmentId){
    return iEmployeeService.getObjectsByDepartment(departmentId);
  }

  @RequestMapping("/employee_management")
  public String showEmployeeManagement(Model model){
    model.addAttribute("employees",iEmployeeService.getAll());
    model.addAttribute("departments",iDepartmentService.getAll());
    return "employees";
  }

  @RequestMapping("/employee_management_by_department")
  public String showEmployeeManagementByDepartment(@RequestParam("id")int id,
                                                   Model model){
    List<EmployeeEntity> employees = iEmployeeService.getAll().stream().filter(employee -> id == employee.getDepartment().getId()).collect(Collectors.toList());
    model.addAttribute("employees", employees);
    return "employees";
  }

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
    }else{
      employee.setCreateDate(new Date());
    }
    iEmployeeService.insertObject(employee);
    return "redirect:/employee_manager";
  }

  @RequestMapping("/update_employee")
  public String showUpdateEmployeePage(@RequestParam("employeeId")int employeeId,
                                       Model model){
    model.addAttribute("employee",iEmployeeService.getObjectById(employeeId));
    model.addAttribute("departments",iDepartmentService.getAll());
    return "/html/Manager/employee/manager-employee-update";
  }
}
