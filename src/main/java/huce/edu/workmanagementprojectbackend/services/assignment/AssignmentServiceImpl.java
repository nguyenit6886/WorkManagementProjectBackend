package huce.edu.workmanagementprojectbackend.services.assignment;

import huce.edu.workmanagementprojectbackend.model.AssignmentDepartmentEntity;
import huce.edu.workmanagementprojectbackend.model.AssignmentEntity;
import huce.edu.workmanagementprojectbackend.model.EmployeeEntity;
import huce.edu.workmanagementprojectbackend.model.TaskEntity;
import huce.edu.workmanagementprojectbackend.paging.Paged;
import huce.edu.workmanagementprojectbackend.repository.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssignmentServiceImpl implements IAssignmentService{

  @Autowired
  private AssignmentRepository repository;

  @Override
  public List<AssignmentEntity> getAll() {
    return repository.findAllActive();
  }

  @Override
  public AssignmentEntity getObjectById(int id) {
    return repository.findById(id).get();
  }

  @Override
  public int insertObject(AssignmentEntity assignmentEntity) {
    try{
      repository.save(assignmentEntity);
      return 200;
    }catch (Exception e){
      e.printStackTrace();
      return 400;
    }
  }

  @Override
  public int updateObject(AssignmentEntity assignmentEntity) {
    try{
      AssignmentEntity assignmentUpdated = repository.findById(assignmentEntity.getId()).get();
      if (assignmentUpdated.getUpdateDate() != assignmentEntity.getUpdateDate())
        assignmentUpdated.setUpdateDate(assignmentEntity.getUpdateDate());
      if (assignmentUpdated.isActive() != assignmentEntity.isActive())
        assignmentUpdated.setActive(assignmentEntity.isActive());
      repository.save(assignmentUpdated);
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
  public List<TaskEntity> getTaskByEmployee(EmployeeEntity employee) {
    return repository.findAllActive().stream().filter(e -> e.getEmployee().equals(employee)).map(AssignmentEntity::getTask).collect(Collectors.toList());
  }

  @Override
  public Paged<AssignmentEntity> getPage(int pageNumber) {
    return null;
  }

  @Override
  public List<AssignmentEntity> getAssignmentByTask(TaskEntity task) {
    try{
      return repository.findAllByTask(task.getId());
    }catch (Exception e){
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public List<AssignmentEntity> getAssignmentByTaskActive(TaskEntity task) {
    try{
      return repository.findAllByTaskActive(task.getId());
    }catch (Exception e){
      e.printStackTrace();
      return null;
    }
  }
}
