package huce.edu.workmanagementprojectbackend.controller;

import huce.edu.workmanagementprojectbackend.model.DepartmentEntity;
import huce.edu.workmanagementprojectbackend.services.department.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
public class DepartmentController {

  @Autowired
  private IDepartmentService iDepartmentService;

  @RequestMapping("/department_manager")
  public String showDepartmentManagerPage(Model model){
    model.addAttribute("departments",iDepartmentService.getAll());
    return "/html/Manager/department/manager-department";
  }

  @RequestMapping("/save_department")
  public String addDepartment(@ModelAttribute("department")DepartmentEntity department) {
    if(department.getId() != 0){
      department.setUpdateDate(new Date());
      iDepartmentService.updateObject(department);
    }else{
      department.setCreateDate(new Date());
      department.setActive(true);
      iDepartmentService.insertObject(department);
    }
    return "redirect:/department_manager";
  }

  @RequestMapping("/delete_department")
  public String deleteDepartment(@RequestParam("departmentId")int departmentId) {
    iDepartmentService.deleteObject(departmentId);
    return "redirect:/department_manager";
  }

}