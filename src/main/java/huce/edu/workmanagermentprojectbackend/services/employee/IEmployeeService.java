package huce.edu.workmanagermentprojectbackend.services.employee;

import huce.edu.workmanagermentprojectbackend.model.EmployeeEntity;
import huce.edu.workmanagermentprojectbackend.services.IFunctionService;

import java.util.List;

public interface IEmployeeService extends IFunctionService<EmployeeEntity> {
  @Override
  List<EmployeeEntity> getAll();

  @Override
  EmployeeEntity getObjectById(int id);

  List<EmployeeEntity> getObjectsByDepartment(int departmentId);
}
