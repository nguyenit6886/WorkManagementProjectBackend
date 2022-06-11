package huce.edu.workmanagermentprojectbackend.controller;

import huce.edu.workmanagermentprojectbackend.model.DepartmentEntity;
import huce.edu.workmanagermentprojectbackend.model.EmployeeEntity;
import huce.edu.workmanagermentprojectbackend.services.department.IDepartmentService;
import huce.edu.workmanagermentprojectbackend.services.employee.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class EmployeeController {

  @Autowired
  private IEmployeeService iEmployeeService;

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
}
