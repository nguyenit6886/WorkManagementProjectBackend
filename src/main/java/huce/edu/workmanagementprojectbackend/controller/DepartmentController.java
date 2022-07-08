package huce.edu.workmanagementprojectbackend.controller;

import huce.edu.workmanagementprojectbackend.model.DepartmentEntity;
import huce.edu.workmanagementprojectbackend.services.department.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class DepartmentController {

  List<String> errors = new ArrayList<>();

  @Autowired
  private IDepartmentService iDepartmentService;

  @RequestMapping("/department_manager")
  public String showDepartmentManagerPage(@RequestParam(value = "pageNumber",required = false, defaultValue = "1") int pageNumber,
                                          Model model,
                                          HttpSession session){
    model.addAttribute("departments",iDepartmentService.getPage(pageNumber));
    model.addAttribute("user",session.getAttribute("user"));
    if(errors.size() > 0){
      StringBuilder string = new StringBuilder();
      for(String error : errors){
        string.append(error);
        string.append('\n');
      }
      model.addAttribute("error",string);
      errors.clear();
    }
    return "/html/Manager/department/manager-department";
  }

  @RequestMapping("/save_department")
  public String addDepartment(@ModelAttribute("department")DepartmentEntity department) {
    if(iDepartmentService.getAll().stream().map(DepartmentEntity::getName).collect(Collectors.toList()).contains(department.getName())){
      errors.add("Tên đã tồn tại");
    }else{
      if(department.getId() != 0){
        department.setUpdateDate(new Date());
        iDepartmentService.updateObject(department);
      }else{
        department.setCreateDate(new Date());
        department.setActive(true);
        iDepartmentService.insertObject(department);
      }
    }
    return "redirect:/department_manager";
  }

  @RequestMapping("/delete_department")
  public String deleteDepartment(@RequestParam("departmentId")int departmentId) {
    iDepartmentService.deleteObject(departmentId);
    return "redirect:/department_manager";
  }

}