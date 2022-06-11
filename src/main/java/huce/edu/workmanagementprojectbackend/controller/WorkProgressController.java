package huce.edu.workmanagementprojectbackend.controller;

import huce.edu.workmanagementprojectbackend.model.WorkProgressEntity;
import huce.edu.workmanagementprojectbackend.services.workprogress.IWorkProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class WorkProgressController {

  @Autowired
  private IWorkProgressService iWorkProgressService;

  @ResponseBody
  @GetMapping("/workprogresss")
  public List<WorkProgressEntity> getDepartments(){
    return iWorkProgressService.getAll();
  }

  @ResponseBody
  @GetMapping("/workprogress_by_id")
  public WorkProgressEntity getDepartmentById(@RequestParam int workprogressId){
    return iWorkProgressService.getObjectById(workprogressId);
  }
}
