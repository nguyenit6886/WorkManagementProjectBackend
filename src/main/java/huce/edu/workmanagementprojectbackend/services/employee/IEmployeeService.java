package huce.edu.workmanagementprojectbackend.services.employee;

import huce.edu.workmanagementprojectbackend.model.EmployeeEntity;
import huce.edu.workmanagementprojectbackend.services.IFunctionService;

import java.util.List;

public interface IEmployeeService extends IFunctionService<EmployeeEntity> {
  @Override
  List<EmployeeEntity> getAll();

  @Override
  EmployeeEntity getObjectById(int id);

  List<EmployeeEntity> getObjectsByDepartment(int departmentId);
}