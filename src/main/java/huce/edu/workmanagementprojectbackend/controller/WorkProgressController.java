package huce.edu.workmanagementprojectbackend.controller;

import huce.edu.workmanagementprojectbackend.services.workprogress.IWorkProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WorkProgressController {

  @Autowired
  private IWorkProgressService iWorkProgressService;

  @RequestMapping("/workprogress_by_task")
  public String deleteEmployee(@RequestParam("taskId")int taskId,
                               Model model) {
    model.addAttribute("workProgresss",iWorkProgressService.getObjectsByTask(taskId));
    return "/html/Employee/employee-workprogress";
  }
}
