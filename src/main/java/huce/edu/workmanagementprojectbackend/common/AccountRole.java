package huce.edu.workmanagementprojectbackend.common;

import huce.edu.workmanagementprojectbackend.model.EmployeeEntity;

public enum AccountRole {
    ROLE_MANAGER(0),
    ROLE_LEADER(1),
    ROLE_EMPLOYEE(2);

    public final int n;
    AccountRole(int n){
        this.n = n;
    }

    public int getValue(){
        return this.n;
    }

    public static AccountRole getAccountRole(int n){
        switch (n){
            case 0:
                return ROLE_MANAGER;
            case 1:
                return ROLE_LEADER;
            case 2:
                return ROLE_EMPLOYEE;
        }
        return ROLE_EMPLOYEE;
    }
}
