package huce.edu.workmanagementprojectbackend.controller;

import huce.edu.workmanagementprojectbackend.model.DepartmentEntity;
import huce.edu.workmanagementprojectbackend.services.department.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class DepartmentController {

  @Autowired
  private IDepartmentService iDepartmentService;

  @RequestMapping("/department_manager")
  public String showDepartmentManagerPage(@RequestParam(value = "pageNumber",required = false, defaultValue = "1") int pageNumber,
                                          Model model,
                                          HttpSession session){
    model.addAttribute("departments",iDepartmentService.getPage(pageNumber));
    model.addAttribute("user",session.getAttribute("user"));
    return "/html/Manager/department/manager-department";
  }

  @RequestMapping("/save_department")
  public String addDepartment(@ModelAttribute("department")DepartmentEntity department,
                              Model model) {
    Map<String,String> errors = new HashMap<>();
    errors.put("Name","Tên đã tồn tại");
    if(errors.keySet().size() > 0){
      model.addAttribute("error",errors);
      model.addAttribute("department",department);
      return "/html/Manager/department/manager-department";
    }
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