package huce.edu.workmanagementprojectbackend.services.assignmentdepartment;

import huce.edu.workmanagementprojectbackend.model.AssignmentDepartmentEntity;
import huce.edu.workmanagementprojectbackend.model.ProjectEntity;
import huce.edu.workmanagementprojectbackend.paging.Paged;
import huce.edu.workmanagementprojectbackend.services.IFunctionService;

import java.util.List;

public interface IAssignmentDepartmentService extends IFunctionService<AssignmentDepartmentEntity> {
  @Override
  List<AssignmentDepartmentEntity> getAll();

  @Override
  AssignmentDepartmentEntity getObjectById(int id);

  @Override
  int insertObject(AssignmentDepartmentEntity assignmentDepartmentEntity);

  @Override
  int updateObject(AssignmentDepartmentEntity assignmentDepartmentEntity);

  @Override
  int deleteObject(int id);

  List<AssignmentDepartmentEntity> getAssignmentDepartmentByProject(ProjectEntity project);

  List<AssignmentDepartmentEntity> getAssignmentDepartmentByProjectAvtive(ProjectEntity project);

  @Override
  Paged<AssignmentDepartmentEntity> getPage(int pageNumber);
}
