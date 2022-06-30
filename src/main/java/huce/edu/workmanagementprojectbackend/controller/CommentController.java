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

  @RequestMapping("/save_comment")
  public String addDepartment(@ModelAttribute("comment")CommentEntity comment) {
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
    return "redirect:/detail_workprogress?workProgressId="+comment.getWorkProgress().getId();
  }
}
