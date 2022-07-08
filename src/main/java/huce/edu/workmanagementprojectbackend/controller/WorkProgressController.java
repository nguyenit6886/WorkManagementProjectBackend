package huce.edu.workmanagementprojectbackend.controller;

import huce.edu.workmanagementprojectbackend.model.EmployeeEntity;
import huce.edu.workmanagementprojectbackend.model.WorkProgressEntity;
import huce.edu.workmanagementprojectbackend.services.comment.ICommentService;
import huce.edu.workmanagementprojectbackend.services.employee.IEmployeeService;
import huce.edu.workmanagementprojectbackend.services.workprogress.IWorkProgressService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.Date;

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
                                                 @RequestParam(value = "pageNumber",required = false, defaultValue = "1") int pageNumber,
                                                 Model model,
                                                 HttpSession session){
    model.addAttribute("workProgresss",iWorkProgressService.getPageByTaskId(taskId,pageNumber));
    model.addAttribute("taskId",taskId);
    model.addAttribute("user",session.getAttribute("user"));
    return "/html/Employee/employee-workprogress";
  }

  @RequestMapping("/manager_workprogress_by_task")
  public String showManagerWorkProgressListPage(@RequestParam("taskId")int taskId,
                                                @RequestParam(value = "pageNumber",required = false, defaultValue = "1") int pageNumber,
                                                Model model,
                                                HttpSession session){
    model.addAttribute("workProgresss",iWorkProgressService.getPageByTaskId(taskId,pageNumber));
    model.addAttribute("taskId",taskId);
    model.addAttribute("user",session.getAttribute("user"));
    return "/html/Manager/project/manager-workprogress";
  }

  @RequestMapping("/leader_workprogress_by_task")
  public String showLeaderWorkProgressListPage(@RequestParam("taskId")int taskId,
                                               @RequestParam(value = "pageNumber",required = false, defaultValue = "1") int pageNumber,
                                               Model model,
                                               HttpSession session){
    model.addAttribute("workProgresses",iWorkProgressService.getPageByTaskId(taskId,pageNumber));
    model.addAttribute("taskId",taskId);
    model.addAttribute("user",session.getAttribute("user"));
    return "/html/Leader/leader-workprogress";
  }

  @RequestMapping("/leader_detail_workprogress")
  public String showLeaderDetailWorkProgressPage(@RequestParam("workProgressId")int workProgressId,
                                                 Model model, HttpSession session){
    EmployeeEntity employee = (EmployeeEntity) session.getAttribute("user");
    if(employee == null){
      return "redirect:/login";
    }
    WorkProgressEntity workProgress = iWorkProgressService.getObjectById(workProgressId);
    model.addAttribute("workProgress",workProgress);
    model.addAttribute("comments",iCommentService.getCommentByWorkProgress(workProgress));
    model.addAttribute("user",employee);
    return "/html/Leader/leader-detail-workprogress";
  }


  @RequestMapping("/employee_detail_workprogress")
  public String showEmployeeDetailWorkProgressPage(@RequestParam("workProgressId")int workProgressId,
                                                   Model model,
                                                   HttpSession session){
    EmployeeEntity employee = (EmployeeEntity) session.getAttribute("user");
    if(employee == null){
      return "redirect:/login";
    }
    WorkProgressEntity workProgress = iWorkProgressService.getObjectById(workProgressId);
    model.addAttribute("workProgress",workProgress);
    model.addAttribute("comments",iCommentService.getCommentByWorkProgress(workProgress));
    model.addAttribute("user",employee);
    if(workProgress.getFileName() != null){
      String[] files = workProgress.getFileName().split(";");
      model.addAttribute("files",files);
    }
    return "/html/Employee/employee-detail-workprogress";
  }

  @RequestMapping("/manager_detail_workprogress")
  public String showManagerDetailWorkProgressPage(@RequestParam("workProgressId")int workProgressId,
                                                  Model model,
                                                  HttpSession session){
    WorkProgressEntity workProgress = iWorkProgressService.getObjectById(workProgressId);
    model.addAttribute("workProgress",workProgress);
    model.addAttribute("comments",iCommentService.getCommentByWorkProgress(workProgress));
    EmployeeEntity user = (EmployeeEntity) session.getAttribute("user");
    if(user != null) {
      model.addAttribute("user", user);
      return "/html/Manager/project/manager-detail-workprogress";
    }
    return "redirect:/login";
  }

  @RequestMapping("/add_workprogress")
  public String showAddWorkProgressPage(@RequestParam("taskId")int taskId,
                                        Model model,
                                        HttpSession session){
    model.addAttribute("taskId",taskId);
    model.addAttribute("user",session.getAttribute("user"));
    return "/html/Employee/employee-detail-workprogress-add";
  }

  @RequestMapping("/save_workprogress")
  public String addWorkProgress(@ModelAttribute("workProgress") WorkProgressEntity workProgress,
                                @RequestParam("files") MultipartFile[] multipartFiles,
                                HttpSession session) {
    int workProgressId;
    if(workProgress.getId() != 0){
      workProgress.setUpdateDate(new Date());
      StringBuilder fileName = new StringBuilder();
      for(MultipartFile multipartFile : multipartFiles){
        fileName.append(multipartFile.getOriginalFilename());
        fileName.append(";");
      }
      workProgress.setFileName(fileName.toString());
      workProgressId = iWorkProgressService.updateObject(workProgress);
      iWorkProgressService.insertFile(multipartFiles, workProgress);
    }else{
      EmployeeEntity employee = (EmployeeEntity) session.getAttribute("user");
      workProgress.setCreateDate(new Date());
      workProgress.setActive(true);
      workProgress.setEmployee(iEmployeeService.getObjectById(employee.getId()));
      StringBuilder fileName = new StringBuilder();
      for(MultipartFile multipartFile : multipartFiles){
        fileName.append(multipartFile.getOriginalFilename());
        fileName.append(";");
      }
      workProgress.setFileName(fileName.toString());
      workProgressId =  iWorkProgressService.insertObject(workProgress);
      iWorkProgressService.insertFile(multipartFiles, workProgress);
    }
    return "redirect:/employee_detail_workprogress?workProgressId="+workProgressId;
  }

  @RequestMapping("/delete_workprogress")
  public String deleteDepartment(@RequestParam("taskId")int taskId,
                                 @RequestParam("workProgressId")int workProgressId) {
    iWorkProgressService.deleteObject(workProgressId);
    return "redirect:/employee_workprogress_by_task?taskId="+taskId;
  }
}
