package huce.edu.workmanagementprojectbackend.controller;

import huce.edu.workmanagementprojectbackend.model.ResultEvaluationEntity;
import huce.edu.workmanagementprojectbackend.services.resultevaluation.IResultEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ResultEvaluationController {

  @Autowired
  private IResultEvaluationService iResultEvaluationService;

  @ResponseBody
  @GetMapping("/resultevaluations")
  public List<ResultEvaluationEntity> getDepartments(){
    return iResultEvaluationService.getAll();
  }

  @ResponseBody
  @GetMapping("/resultevaluation_by_id")
  public ResultEvaluationEntity getDepartmentById(@RequestParam int resultevaluationId){
    return iResultEvaluationService.getObjectById(resultevaluationId);
  }
}
