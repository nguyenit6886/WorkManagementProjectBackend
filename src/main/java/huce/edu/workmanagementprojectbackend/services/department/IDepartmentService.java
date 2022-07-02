package huce.edu.workmanagementprojectbackend.services.department;

import huce.edu.workmanagementprojectbackend.model.DepartmentEntity;
import huce.edu.workmanagementprojectbackend.paging.Paged;
import huce.edu.workmanagementprojectbackend.services.IFunctionService;

import java.util.List;

public interface IDepartmentService extends IFunctionService<DepartmentEntity> {
  List<DepartmentEntity> getAll();

  DepartmentEntity getObjectById(int id);

  @Override
  int insertObject(DepartmentEntity departmentEntity);

  @Override
  int updateObject(DepartmentEntity departmentEntity);

  @Override
  int deleteObject(int id);

  @Override
  Paged<DepartmentEntity> getPage(int pageNumber);
}
