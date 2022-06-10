package huce.edu.workmanagermentprojectbackend.controller;

import huce.edu.workmanagermentprojectbackend.model.DepartmentEntity;
import huce.edu.workmanagermentprojectbackend.services.department.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DepartmentController {

  @Autowired
  private IDepartmentService iDepartmentService;

  @ResponseBody
  @GetMapping("/list_department")
  public List<DepartmentEntity> getDepartments(){
    return iDepartmentService.getAll();
  }

  @ResponseBody
  @GetMapping("/departmentbyid")
  public DepartmentEntity getDepartmentById(@RequestParam int departmentId){
    return iDepartmentService.getObjectById(departmentId);
  }
}
