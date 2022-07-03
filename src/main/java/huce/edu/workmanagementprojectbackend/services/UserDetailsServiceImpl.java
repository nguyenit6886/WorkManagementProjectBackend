package huce.edu.workmanagementprojectbackend.services;

import huce.edu.workmanagementprojectbackend.common.AccountRole;
import huce.edu.workmanagementprojectbackend.model.EmployeeEntity;
import huce.edu.workmanagementprojectbackend.services.employee.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.authentication.PasswordEncoderParser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private IEmployeeService iEmployeeService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        EmployeeEntity user = this.iEmployeeService.getEmployeeByUsername(username);

        if (user == null) {
            System.out.println("User not found! " + username);
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }

        int role = user.getPosition();
        AccountRole accountRole = AccountRole.getAccountRole(role);
        List<String> roleNames = new ArrayList<>();
        roleNames.add(accountRole.name());

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();

        if (roleNames != null) {
            for (String r : roleNames) {
                GrantedAuthority authority = new SimpleGrantedAuthority(r);
                grantList.add(authority);
            }
        }

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordBcrypt = passwordEncoder.encode(user.getPasswordHash());
        UserDetails userDetails = new User(user.getUsername(),
                passwordBcrypt, grantList);
        return userDetails;
    }
}
