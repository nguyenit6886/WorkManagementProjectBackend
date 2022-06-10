package huce.edu.workmanagermentprojectbackend.services.department;

import huce.edu.workmanagermentprojectbackend.model.DepartmentEntity;
import huce.edu.workmanagermentprojectbackend.services.IFunctionService;

import java.util.List;

public interface IDepartmentService extends IFunctionService<DepartmentEntity> {
  List<DepartmentEntity> getAll();

  DepartmentEntity getObjectById(int id);
}
