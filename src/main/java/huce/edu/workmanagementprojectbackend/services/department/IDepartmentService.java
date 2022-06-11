package huce.edu.workmanagementprojectbackend.services.department;

import huce.edu.workmanagementprojectbackend.model.DepartmentEntity;
import huce.edu.workmanagementprojectbackend.services.IFunctionService;

import java.util.List;

public interface IDepartmentService extends IFunctionService<DepartmentEntity> {
  List<DepartmentEntity> getAll();

  DepartmentEntity getObjectById(int id);
}
