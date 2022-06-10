package huce.edu.workmanagermentprojectbackend.services.employee;

import huce.edu.workmanagermentprojectbackend.model.DepartmentEntity;
import huce.edu.workmanagermentprojectbackend.model.EmployeeEntity;
import huce.edu.workmanagermentprojectbackend.repository.DepartmentRepository;
import huce.edu.workmanagermentprojectbackend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements IEmployeeService{

  @Autowired
  private EmployeeRepository repository;

  @Autowired
  private DepartmentRepository departmentRepository;

  @Override
  public List<EmployeeEntity> getAll() {
    return repository.findAll();
  }

  @Override
  public EmployeeEntity getObjectById(int id) {
    return repository.findById(id).get();
  }

  public List<EmployeeEntity> getObjectsByDepartment(int departmentId){
    DepartmentEntity department = departmentRepository.findById(departmentId).get();
    return repository.findAll().stream()
        .filter(e -> e.getDepartment().equals(department)).collect(Collectors.toList());
  }
}
