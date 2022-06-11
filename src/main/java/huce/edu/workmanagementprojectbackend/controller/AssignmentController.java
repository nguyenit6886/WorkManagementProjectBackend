package huce.edu.workmanagementprojectbackend.controller;

import huce.edu.workmanagementprojectbackend.model.AssignmentEntity;
import huce.edu.workmanagementprojectbackend.services.assignment.IAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AssignmentController {

  @Autowired
  private IAssignmentService iAssignmentService;

  @ResponseBody
  @GetMapping("/assignments")
  public List<AssignmentEntity> getDepartments(){
    return iAssignmentService.getAll();
  }

  @ResponseBody
  @GetMapping("/assignment_by_id")
  public AssignmentEntity getDepartmentById(@RequestParam int assignmentId){
    return iAssignmentService.getObjectById(assignmentId);
  }
}
