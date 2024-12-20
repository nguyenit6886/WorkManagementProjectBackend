package huce.edu.workmanagementprojectbackend.services.employee;

import huce.edu.workmanagementprojectbackend.model.EmployeeEntity;
import huce.edu.workmanagementprojectbackend.paging.Paged;
import huce.edu.workmanagementprojectbackend.services.IFunctionService;

import java.util.List;

public interface IEmployeeService extends IFunctionService<EmployeeEntity> {
  @Override
  List<EmployeeEntity> getAll();

  @Override
  EmployeeEntity getObjectById(int id);

  List<EmployeeEntity> getObjectsByDepartment(int departmentId);

  EmployeeEntity getEmployeeForLogin(String username, String password);
  EmployeeEntity getEmployeeByUsername(String username);
  @Override
  int insertObject(EmployeeEntity employeeEntity);

  @Override
  int updateObject(EmployeeEntity employeeEntity);

  @Override
  int deleteObject(int id);

  @Override
  Paged<EmployeeEntity> getPage(int pageNumber);
}
