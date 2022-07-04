package huce.edu.workmanagementprojectbackend.controller;

import huce.edu.workmanagementprojectbackend.model.WorkProgressEntity;
import huce.edu.workmanagementprojectbackend.services.comment.ICommentService;
import huce.edu.workmanagementprojectbackend.services.employee.IEmployeeService;
import huce.edu.workmanagementprojectbackend.services.workprogress.IWorkProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class WorkProgressController {

  @Autowired
  private IWorkProgressService iWorkProgressService;

  @Autowired
  private ICommentService iCommentService;

  @Autowired
  private IEmployeeService iEmployeeService;

  @RequestMapping("/employee_workprogress_by_task")
  public String showEmployeeWorkProgressListPage(@RequestParam("taskId")int taskId,
                                                 Model model){
    model.addAttribute("workProgresss",iWorkProgressService.getObjectsByTask(taskId));
    model.addAttribute("taskId",taskId);
    return "/html/Employee/employee-workprogress";
  }

  @RequestMapping("/manager_workprogress_by_task")
  public String showManagerWorkProgressListPage(@RequestParam("taskId")int taskId,
                                                Model model){
    model.addAttribute("workProgresss",iWorkProgressService.getObjectsByTask(taskId));
    model.addAttribute("taskId",taskId);
    return "/html/Manager/project/manager-workprogress";
  }

  @RequestMapping("/employee_detail_workprogress")
  public String showEmployeeDetailWorkProgressPage(@RequestParam("workProgressId")int workProgressId,
                                           Model model){
    WorkProgressEntity workProgress = iWorkProgressService.getObjectById(workProgressId);
    model.addAttribute("workProgress",workProgress);
    model.addAttribute("comments",iCommentService.getCommentByWorkProgress(workProgress));
    if(workProgress.getFileName() != null){
      String[] files = workProgress.getFileName().split(";");
      model.addAttribute("files",files);
    }
    return "/html/Employee/employee-detail-workprogress";
  }

  @RequestMapping("/manager_detail_workprogress")
  public String showManagerDetailWorkProgressPage(@RequestParam("workProgressId")int workProgressId,
                                           Model model){
    WorkProgressEntity workProgress = iWorkProgressService.getObjectById(workProgressId);
    model.addAttribute("workProgress",workProgress);
    model.addAttribute("comments",iCommentService.getCommentByWorkProgress(workProgress));
    return "/html/Manager/project/manager-detail-workprogress";
  }

  @RequestMapping("/add_workprogress")
  public String showAddWorkProgressPage(@RequestParam("taskId")int taskId,
                                        Model model){
    model.addAttribute("taskId",taskId);
    return "/html/Employee/employee-detail-workprogress-add";
  }

  @RequestMapping("/save_workprogress")
  public String addWorkProgress(@ModelAttribute("workProgress") WorkProgressEntity workProgress,
                                @RequestParam("files") MultipartFile[] multipartFiles) {
    if(workProgress.getId() != 0){
      workProgress.setUpdateDate(new Date());
      iWorkProgressService.updateObject(workProgress);
    }else{
      workProgress.setCreateDate(new Date());
      workProgress.setActive(true);
      workProgress.setEmployee(iEmployeeService.getObjectById(LoginController.CREATE_USER_ID));
      StringBuilder fileName = new StringBuilder();
      for(MultipartFile multipartFile : multipartFiles){
        fileName.append(multipartFile.getOriginalFilename());
        fileName.append(";");
      }
      workProgress.setFileName(fileName.toString());
      iWorkProgressService.insertObject(workProgress);
      iWorkProgressService.insertImage(multipartFiles, workProgress);
    }
    return "redirect:/employee_workprogress_by_task?taskId="+workProgress.getTask().getId();
  }

  @RequestMapping("/delete_workprogress")
  public String deleteDepartment(@RequestParam("taskId")int taskId,
                                 @RequestParam("workProgressId")int workProgressId) {
    iWorkProgressService.deleteObject(workProgressId);
    return "redirect:/employee_workprogress_by_task?taskId="+taskId;
  }
}
