package huce.edu.workmanagementprojectbackend.controller;

import huce.edu.workmanagementprojectbackend.model.EmployeeEntity;
import huce.edu.workmanagementprojectbackend.services.department.IDepartmentService;
import huce.edu.workmanagementprojectbackend.services.employee.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Controller
public class EmployeeController {

  private List<String> errors = new ArrayList<>();

  @Autowired
  private IEmployeeService iEmployeeService;

  @Autowired
  private IDepartmentService iDepartmentService;

  @RequestMapping("/employee_manager")
  public String showEmployeeManagerPage(Model model,
                                        HttpSession session){
    model.addAttribute("employees",iEmployeeService.getAll());
    model.addAttribute("user",session.getAttribute("user"));
    sentError(model);
    return "/html/Manager/employee/manager-employee";
  }

  @RequestMapping("/add_employee")
  public String showAddEmployeePage(Model model,
                                    HttpSession session){
    model.addAttribute("departments",iDepartmentService.getAll());
    model.addAttribute("user",session.getAttribute("user"));
    sentError(model);
    return "/html/Manager/employee/manager-employee-add";
  }

  @PostMapping("/save_employee_action")
  public String addEmployee(@ModelAttribute("employee") EmployeeEntity employee){
    if(validateSave(employee)){
      if(employee.getId() != 0){
        employee.setUpdateDate(new Date());
        iEmployeeService.updateObject(employee);
      }else{
        employee.setCreateDate(new Date());
        employee.setActive(true);
        iEmployeeService.insertObject(employee);
      }
      return "redirect:/employee_manager";
    }else{
      if(employee.getId() != 0){
        return "redirect:/update_employee?employeeId="+employee.getId();
      }else{
        return "redirect:/add_employee";
      }
    }
  }

  @RequestMapping("/update_employee")
  public String showUpdateEmployeePage(@RequestParam("employeeId")int employeeId,
                                       Model model,
                                       HttpSession session){
    model.addAttribute("employee",iEmployeeService.getObjectById(employeeId));
    model.addAttribute("departments",iDepartmentService.getAll());
    model.addAttribute("user",session.getAttribute("user"));
    sentError(model);
    return "/html/Manager/employee/manager-employee-update";
  }

  @RequestMapping("/delete_employee")
  public String deleteEmployee(@RequestParam("employeeId")int employeeId) {
    if(validateDelete(employeeId)){
      iEmployeeService.deleteObject(employeeId);
    }
    return "redirect:/employee_manager";
  }

  @RequestMapping("/employees_of_department")
  public String showEmployeesOfDepartmentPage(@RequestParam("departmentId")int departmentId,
                                              Model model,
                                              HttpSession session){
    model.addAttribute("employees",iEmployeeService.getObjectsByDepartment(departmentId));
    model.addAttribute("user",session.getAttribute("user"));
    return "/html/Manager/department/manager-employeeOfDepartment";
  }

  private boolean validateSave(EmployeeEntity employee){
    boolean valid = true;
    if(employee.getFirstName().isEmpty()){
      errors.add("Chưa nhập họ");
      return false;
    }
    if(employee.getLastName().isEmpty()){
      errors.add("Chưa nhập tên");
      return false;
    }
    if(!checkRegularExpression("^[_A-Za-z0-9]+(\\.[_A-Za-z0-9]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",employee.getEmail())){
      errors.add("Email không đúng định dạng");
      valid = false;
    }
    if(!checkRegularExpression("^[0-9]+$", employee.getPhone())){
      errors.add("Không đúng định dạng số điện thoại");
      valid = false;
    }
    if(employee.getBirthday() != null && employee.getBirthday().after(new Date())){
      errors.add("Ngày sinh không thể lớn hơn ngày hiện tại");
      valid = false;
    }
    if(employee.getId() != 0){
      if(!iEmployeeService.getObjectById(employee.getId()).getEmail().equals(employee.getEmail())){
        if(iEmployeeService.getAll().stream().map(EmployeeEntity::getEmail).collect(Collectors.toList()).contains(employee.getEmail())){
          errors.add("Email đã tồn tại");
          valid = false;
        }
      }
      if(!iEmployeeService.getObjectById(employee.getId()).getPhone().equals(employee.getPhone())){
        if(iEmployeeService.getAll().stream().map(EmployeeEntity::getPhone).collect(Collectors.toList()).contains(employee.getPhone())){
          errors.add("Số điện thoại đã tồn tại");
          valid = false;
        }
      }
    }else{
      if(iEmployeeService.getAll().stream().map(EmployeeEntity::getEmail).collect(Collectors.toList()).contains(employee.getEmail())){
        errors.add("Email đã tồn tại");
        valid = false;
      }
      if(iEmployeeService.getAll().stream().map(EmployeeEntity::getPhone).collect(Collectors.toList()).contains(employee.getPhone())){
        errors.add("Số điện thoại đã tồn tại");
        valid = false;
      }
    }
    return valid;
  }

  private boolean validateDelete(int employeeId){
    if(iEmployeeService.getAll().stream().map(EmployeeEntity::getId).collect(Collectors.toList()).contains(employeeId)){
      return true;
    }else{
      errors.add("Nhân viên không tồn tại");
      return false;
    }
  }

  private boolean checkRegularExpression(String regex, String str) {
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(str);
    if (matcher.find()) {
      return true;
    }
    return false;
  }

  private void sentError(Model model){
    if(errors.size() > 0){
      StringBuilder string = new StringBuilder();
      for(String error : errors){
        string.append(error);
        string.append('\n');
      }
      model.addAttribute("error",string);
      errors.clear();
    }
  }
}
