package huce.edu.workmanagementprojectbackend.services.department;

import huce.edu.workmanagementprojectbackend.model.DepartmentEntity;
import huce.edu.workmanagementprojectbackend.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements IDepartmentService{

  @Autowired
  private DepartmentRepository repository;

  @Override
  public List<DepartmentEntity> getAll() {
    return repository.findAll();
  }

  @Override
  public DepartmentEntity getObjectById(int id) {
    return repository.findById(id).get();
  }
}
