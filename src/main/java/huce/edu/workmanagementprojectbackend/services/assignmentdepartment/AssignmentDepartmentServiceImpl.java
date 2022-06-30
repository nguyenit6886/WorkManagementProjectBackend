package huce.edu.workmanagementprojectbackend.services.assignmentdepartment;

import huce.edu.workmanagementprojectbackend.model.AssignmentDepartmentEntity;
import huce.edu.workmanagementprojectbackend.model.DepartmentEntity;
import huce.edu.workmanagementprojectbackend.model.ProjectEntity;
import huce.edu.workmanagementprojectbackend.repository.AssignmentDepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentDepartmentServiceImpl implements IAssignmentDepartmentService{

  @Autowired
  private AssignmentDepartmentRepository repository;

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
    try{
      repository.save(assignmentDepartmentEntity);
      return 200;
    }catch (Exception e){
      e.printStackTrace();
      return 400;
    }
  }

  @Override
  public int updateObject(AssignmentDepartmentEntity assignmentDepartmentEntity) {
    try{
      AssignmentDepartmentEntity assignmentDepartmentUpdated = repository.findById(assignmentDepartmentEntity.getId()).get();
      if (assignmentDepartmentUpdated.getUpdateDate() != assignmentDepartmentEntity.getUpdateDate())
        assignmentDepartmentUpdated.setUpdateDate(assignmentDepartmentEntity.getUpdateDate());
      if (assignmentDepartmentUpdated.isActive() != assignmentDepartmentEntity.isActive())
        assignmentDepartmentUpdated.setActive(assignmentDepartmentEntity.isActive());
      repository.save(assignmentDepartmentUpdated);
      return 200;
    }catch (Exception e){
      e.printStackTrace();
      return 400;
    }
  }

  @Override
  public int deleteObject(int id) {
    return 0;
  }

  @Override
  public List<AssignmentDepartmentEntity> getAssignmentDepartmentByProject(ProjectEntity project) {
    try{
      return repository.findAllByProject(project.getId());
    }catch (Exception e){
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public List<AssignmentDepartmentEntity> getAssignmentDepartmentByProjectAvtive(ProjectEntity project) {
    try{
      return repository.findAllByProjectActive(project.getId());
    }catch (Exception e){
      e.printStackTrace();
      return null;
    }
  }
}
