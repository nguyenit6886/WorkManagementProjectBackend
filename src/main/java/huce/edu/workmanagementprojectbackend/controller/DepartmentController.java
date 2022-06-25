package huce.edu.workmanagementprojectbackend.controller;

import huce.edu.workmanagementprojectbackend.model.DepartmentEntity;
import huce.edu.workmanagementprojectbackend.services.department.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class DepartmentController {

  @Autowired
  private IDepartmentService iDepartmentService;

//  @ResponseBody
//  @GetMapping("/departments")
//  public List<DepartmentEntity> getDepartments() {
//    return iDepartmentService.getAll();
//  }
//
//  @ResponseBody
//  @GetMapping("/department_by_id")
//  public DepartmentEntity getDepartmentById(@RequestParam int departmentId) {
//    return iDepartmentService.getObjectById(departmentId);
//  }

  @RequestMapping("/department_manager")
  public String showDepartmentManagerPage(Model model){
    model.addAttribute("departments",iDepartmentService.getAll());
    return "/html/Manager/department/manager-department";
  }

  @RequestMapping("/save_department")
  public String addDepartment(DepartmentEntity department) {
    if(department.getId() != 0){
      department.setUpdateDate(new Date());
    }else{
      department.setCreateDate(new Date());
    }
    iDepartmentService.insertObject(department);
    return "redirect:/department_manager";
  }

//  @ResponseBody
//  @PostMapping("/update_department")
//  public int updateDepartment(DepartmentEntity department) {
//    department.setUpdateDate(new Date());
//    return iDepartmentService.updateObject(department);
//  }

  @RequestMapping("/delete_department")
  public String deleteDepartment(@RequestParam("departmentId")int departmentId) {
    iDepartmentService.deleteObject(departmentId);
    return "redirect:/department_manager";
  }

//  @RequestMapping("/department_management")
//  public String showDepartmentManagementPage(Model model){
//    model.addAttribute("departments",iDepartmentService.getAll());
//    return "departments";
//  }
}