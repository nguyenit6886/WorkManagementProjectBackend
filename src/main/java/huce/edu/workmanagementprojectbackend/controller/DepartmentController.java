package huce.edu.workmanagementprojectbackend.controller;

import huce.edu.workmanagementprojectbackend.model.DepartmentEntity;
import huce.edu.workmanagementprojectbackend.services.department.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class DepartmentController {

  @Autowired
  private IDepartmentService iDepartmentService;

  @ResponseBody
  @GetMapping("/departments")
  public List<DepartmentEntity> getDepartments() {
    return iDepartmentService.getAll();
  }

  @ResponseBody
  @GetMapping("/department_by_id")
  public DepartmentEntity getDepartmentById(@RequestParam int departmentId) {
    return iDepartmentService.getObjectById(departmentId);
  }

  @ResponseBody
  @PostMapping("/add_department")
  public int addDepartment(DepartmentEntity department) {
    department.setCreateDate(new Date());
    return iDepartmentService.insertObject(department);
  }

  @ResponseBody
  @PostMapping("/update_department")
  public int updateDepartment(DepartmentEntity department) {
    department.setUpdateDate(new Date());
    return iDepartmentService.updateObject(department);
  }

  @ResponseBody
  @PostMapping("/delete_department")
  public int updateDepartment(int departmentId) {
    return iDepartmentService.deleteObject(departmentId);
  }
}