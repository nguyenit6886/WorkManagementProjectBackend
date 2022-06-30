package huce.edu.workmanagementprojectbackend.controller;

import huce.edu.workmanagementprojectbackend.model.WorkProgressEntity;
import huce.edu.workmanagementprojectbackend.services.comment.ICommentService;
import huce.edu.workmanagementprojectbackend.services.workprogress.IWorkProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class WorkProgressController {

  @Autowired
  private IWorkProgressService iWorkProgressService;

  @Autowired
  private ICommentService iCommentService;

  @RequestMapping("/workprogress_by_task")
  public String showWorkProgressListPage(@RequestParam("taskId")int taskId,
                                         Model model){
    model.addAttribute("workProgresss",iWorkProgressService.getObjectsByTask(taskId));
    model.addAttribute("taskId",taskId);
    return "/html/Employee/employee-workprogress";
  }

  @RequestMapping("/detail_workprogress")
  public String showDetailWorkProgressPage(@RequestParam("workProgressId")int workProgressId,
                                           Model model){
    WorkProgressEntity workProgress = iWorkProgressService.getObjectById(workProgressId);
    model.addAttribute("workProgress",workProgress);
    model.addAttribute("comments",iCommentService.getCommentByWorkProgress(workProgress));
    return "/html/Employee/employee-detail-workprogress";
  }

  @RequestMapping("/add_workprogress")
  public String showAddWorkProgressPage(@RequestParam("taskId")int taskId,
                                        Model model){
    model.addAttribute("taskId",taskId);
    return "/html/Employee/employee-detail-workprogress-add";
  }

  @RequestMapping("/save_workprogress")
  public String addWorkProgress(@ModelAttribute("workProgress") WorkProgressEntity workProgress) {
    if(workProgress.getId() != 0){
      workProgress.setUpdateDate(new Date());
      iWorkProgressService.updateObject(workProgress);
    }else{
      workProgress.setCreateDate(new Date());
      workProgress.setActive(true);
      iWorkProgressService.insertObject(workProgress);
    }
    return "redirect:/workprogress_by_task?taskId="+workProgress.getTask().getId();
  }

  @RequestMapping("/delete_workprogress")
  public String deleteDepartment(@RequestParam("taskId")int taskId,
                                 @RequestParam("workProgressId")int workProgressId) {
    iWorkProgressService.deleteObject(workProgressId);
    return "redirect:/workprogress_by_task?taskId="+taskId;
  }
}
