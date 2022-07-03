package huce.edu.workmanagementprojectbackend.controller;

import huce.edu.workmanagementprojectbackend.services.employee.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;

@Controller
public class CoreController {

    @Autowired
    private IEmployeeService employeeService;

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();

            String userInfo = loginedUser.toString();
            model.addAttribute("userInfo", userInfo);
            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);
        }

        return "/html/403Page";
    }

    @RequestMapping(value = "/loginSuccessfully", method = RequestMethod.GET)
    public String loginSuccess(Model model, Principal principal, HttpSession session) {

        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        ArrayList<GrantedAuthority> grantedAuthorityList = new ArrayList<>(loginedUser.getAuthorities());
        session.setAttribute("user",employeeService.getEmployeeByUsername(loginedUser.getUsername()));
        switch (grantedAuthorityList.get(0).getAuthority()){
            case "ROLE_MANAGER":
                return "redirect:/manager";
            case "ROLE_LEADER":
                return "redirect:/leader_manager";
            case "ROLE_EMPLOYEE":
                return "redirect:/tasks";
        }
        return "";
    }
}
