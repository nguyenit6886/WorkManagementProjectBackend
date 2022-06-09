package huce.edu.workmanagermentprojectbackend.services;

import huce.edu.workmanagermentprojectbackend.model.DepartmentEntity;
import huce.edu.workmanagermentprojectbackend.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements IDepartmentService{

  @Autowired
  private DepartmentRepository repository;

  @Override
  public List<DepartmentEntity> getDepartments() {
    return repository.findAll();
  }
}
