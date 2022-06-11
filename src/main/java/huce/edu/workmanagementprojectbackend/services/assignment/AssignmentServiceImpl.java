package huce.edu.workmanagementprojectbackend.services.assignment;

import huce.edu.workmanagementprojectbackend.model.AssignmentEntity;
import huce.edu.workmanagementprojectbackend.repository.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AssignmentServiceImpl implements IAssignmentService{

  @Autowired
  private AssignmentRepository repository;

  @Override
  public List<AssignmentEntity> getAll() {
    return repository.findAll();
  }

  @Override
  public AssignmentEntity getObjectById(int id) {
    return repository.findById(id).get();
  }
}
