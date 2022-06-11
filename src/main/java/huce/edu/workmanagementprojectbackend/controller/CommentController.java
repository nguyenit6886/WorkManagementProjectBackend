package huce.edu.workmanagementprojectbackend.controller;

import huce.edu.workmanagementprojectbackend.model.CommentEntity;
import huce.edu.workmanagementprojectbackend.services.comment.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CommentController {

  @Autowired
  private ICommentService iCommentService;

  @ResponseBody
  @GetMapping("/comments")
  public List<CommentEntity> getDepartments(){
    return iCommentService.getAll();
  }

  @ResponseBody
  @GetMapping("/comment_by_id")
  public CommentEntity getDepartmentById(@RequestParam int commentId){
    return iCommentService.getObjectById(commentId);
  }
}