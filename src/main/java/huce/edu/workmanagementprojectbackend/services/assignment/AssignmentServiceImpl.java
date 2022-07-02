package huce.edu.workmanagementprojectbackend.services.assignment;

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
    return 0;
  }

  @Override
  public int updateObject(AssignmentEntity assignmentEntity) {
    return 0;
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
}
