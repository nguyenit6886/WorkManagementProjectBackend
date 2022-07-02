package huce.edu.workmanagementprojectbackend.controller;

import huce.edu.workmanagementprojectbackend.model.CommentEntity;
import huce.edu.workmanagementprojectbackend.services.comment.ICommentService;
import huce.edu.workmanagementprojectbackend.services.employee.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
public class CommentController {

  @Autowired
  private ICommentService iCommentService;

  @Autowired
  private IEmployeeService iEmployeeService;

  @RequestMapping("/save_employee_comment")
  public String addEmployeeComment(@ModelAttribute("comment")CommentEntity comment) {
    addCommentAction(comment);
    return "redirect:/employee_detail_workprogress?workProgressId="+comment.getWorkProgress().getId();
  }

  @RequestMapping("/save_manager_comment")
  public String addManagerComment(@ModelAttribute("comment")CommentEntity comment) {
    addCommentAction(comment);
    return "redirect:/manager_detail_workprogress?workProgressId="+comment.getWorkProgress().getId();
  }

  private void addCommentAction(CommentEntity comment){
    if(comment.getId() != 0){
      comment.setUpdateDate(new Date());
      iCommentService.updateObject(comment);
    }else{
      comment.setCreateDate(new Date());
      comment.setActive(true);
      comment.setEmployee(iEmployeeService.getObjectById(LoginController.CREATE_USER_ID));
      comment.setCreateUser(LoginController.CREATE_USER_ID);
      iCommentService.insertObject(comment);
    }
  }
}
