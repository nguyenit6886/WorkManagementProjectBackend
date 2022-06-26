package huce.edu.workmanagementprojectbackend.services.assignment;

import huce.edu.workmanagementprojectbackend.model.AssignmentEntity;
import huce.edu.workmanagementprojectbackend.repository.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
