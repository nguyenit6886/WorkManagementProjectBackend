package huce.edu.workmanagementprojectbackend.controller;

import huce.edu.workmanagementprojectbackend.model.CommentEntity;
import huce.edu.workmanagementprojectbackend.model.EmployeeEntity;
import huce.edu.workmanagementprojectbackend.model.TaskEntity;
import huce.edu.workmanagementprojectbackend.model.WorkProgressEntity;
import huce.edu.workmanagementprojectbackend.services.comment.ICommentService;
import huce.edu.workmanagementprojectbackend.services.employee.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CommentController {

//  private List<String> errors = new ArrayList<>();

  @Autowired
  private ICommentService iCommentService;

  @Autowired
  private IEmployeeService iEmployeeService;

  @RequestMapping("/save_comment")
  public String addComment(@ModelAttribute("comment")CommentEntity comment,
                           HttpSession session) {
    if(validateSave(comment)){
      EmployeeEntity employee = (EmployeeEntity) session.getAttribute("user");
      comment.setEmployee(employee);
      addCommentAction(comment);
    }
    if(comment.getEmployee().getPosition() == 0){
      return "redirect:/manager_detail_workprogress?workProgressId="+comment.getWorkProgress().getId();
    }else if(comment.getEmployee().getPosition() == 1){
      return "redirect:/leader_detail_workprogress?workProgressId="+comment.getWorkProgress().getId();
    }else{
      return "redirect:/employee_detail_workprogress?workProgressId="+comment.getWorkProgress().getId();
    }
  }

  public void addCommentAction(CommentEntity comment){
    if(comment.getId() != 0){
      comment.setUpdateDate(new Date());
      iCommentService.updateObject(comment);
    }else{
      comment.setCreateDate(new Date());
      comment.setActive(true);
      comment.setEmployee(iEmployeeService.getObjectById(comment.getEmployee().getId()));
      comment.setCreateUser(comment.getEmployee().getId());
      iCommentService.insertObject(comment);
    }
  }

  @RequestMapping("/delete_comment")
  public String deleteComment(@RequestParam("commentId")int commentId) {
    if(validateDelete(commentId)){
      iCommentService.deleteObject(commentId);
      CommentEntity comment = iCommentService.getObjectById(commentId);
      if(comment.getEmployee().getPosition() == 0){
        return "redirect:/manager_detail_workprogress?workProgressId="+comment.getWorkProgress().getId();
      }else if(comment.getEmployee().getPosition() == 1){
        return "redirect:/leader_detail_workprogress?workProgressId="+comment.getWorkProgress().getId();
      }else{
        return "redirect:/employee_detail_workprogress?workProgressId="+comment.getWorkProgress().getId();
      }
    }else{
      return "redirect:/login";
    }
  }

  private boolean validateSave(CommentEntity comment){
    boolean valid = true;
    if(comment.getContent().isEmpty()){
//      errors.add("Chưa nhập bình luận");
      return false;
    }
    return valid;
  }

  private boolean validateDelete(int commentId){
    if(iCommentService.getAll().stream().map(CommentEntity::getId).collect(Collectors.toList()).contains(commentId)){
      return true;
    }else{
//      errors.add("Bình luận không tồn tại");
      return false;
    }
  }

//  private void sentError(Model model){
//    if(errors.size() > 0){
//      StringBuilder string = new StringBuilder();
//      for(String error : errors){
//        string.append(error);
//        string.append('\n');
//      }
//      model.addAttribute("error",string);
//      errors.clear();
//    }
//  }
}
