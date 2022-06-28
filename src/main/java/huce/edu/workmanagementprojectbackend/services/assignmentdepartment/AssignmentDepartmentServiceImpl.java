package huce.edu.workmanagementprojectbackend.services.assignmentdepartment;

import huce.edu.workmanagementprojectbackend.model.AssignmentDepartmentEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentDepartmentServiceImpl implements IAssignmentDepartmentService{
  @Override
  public List<AssignmentDepartmentEntity> getAll() {
    return null;
  }

  @Override
  public AssignmentDepartmentEntity getObjectById(int id) {
    return null;
  }

  @Override
  public int insertObject(AssignmentDepartmentEntity assignmentDepartmentEntity) {
    return 0;
  }

  @Override
  public int updateObject(AssignmentDepartmentEntity assignmentDepartmentEntity) {
    return 0;
  }

  @Override
  public int deleteObject(int id) {
    return 0;
  }
}
