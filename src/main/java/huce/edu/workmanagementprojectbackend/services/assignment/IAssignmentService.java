package huce.edu.workmanagementprojectbackend.services.assignment;

import huce.edu.workmanagementprojectbackend.model.AssignmentEntity;
import huce.edu.workmanagementprojectbackend.paging.Paged;
import huce.edu.workmanagementprojectbackend.services.IFunctionService;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IAssignmentService extends IFunctionService<AssignmentEntity> {
  @Override
  List<AssignmentEntity> getAll();

  @Override
  AssignmentEntity getObjectById(int id);

  @Override
  int insertObject(AssignmentEntity assignmentEntity);

  @Override
  int updateObject(AssignmentEntity assignmentEntity);

  @Override
  int deleteObject(int id);

  @Override
  Paged<AssignmentEntity> getPage(int pageNumber);
}
