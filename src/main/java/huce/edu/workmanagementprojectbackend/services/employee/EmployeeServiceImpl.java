package huce.edu.workmanagementprojectbackend.services.employee;

import huce.edu.workmanagementprojectbackend.model.DepartmentEntity;
import huce.edu.workmanagementprojectbackend.model.EmployeeEntity;
import huce.edu.workmanagementprojectbackend.repository.DepartmentRepository;
import huce.edu.workmanagementprojectbackend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements IEmployeeService{

  @Autowired
  private EmployeeRepository repository;

  @Autowired
  private DepartmentRepository departmentRepository;

  @Override
  public List<EmployeeEntity> getAll() {
    return repository.findAllActive();
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

  @Override
  public EmployeeEntity getObjectForLogin(String username) {
    List<EmployeeEntity> employees = repository.findAll();
    for(EmployeeEntity employee : employees){
      if(employee.getUsername().equals(username)){
        return employee;
      }
    }
    return null;
  }


  @Override
  public int insertObject(EmployeeEntity employeeEntity) {
    try{
      repository.save(employeeEntity);
      return 200;
    }catch (Exception e){
      e.printStackTrace();
      return 400;
    }
  }

  @Override
  public int updateObject(EmployeeEntity employeeEntity) {
    try{
      EmployeeEntity employeeEntityUpdated = repository.findById(employeeEntity.getId()).get();
      if(!employeeEntityUpdated.getFirstName().equals(employeeEntity.getFirstName())){
        employeeEntityUpdated.setFirstName(employeeEntity.getFirstName());
      }
      if(!employeeEntityUpdated.getLastName().equals(employeeEntity.getLastName())){
        employeeEntityUpdated.setLastName(employeeEntity.getLastName());
      }
      if(employeeEntityUpdated.getBirthday() != employeeEntity.getBirthday()){
        employeeEntityUpdated.setBirthday(employeeEntity.getBirthday());
      }
      if(!employeeEntityUpdated.getAddress().equals(employeeEntity.getAddress())){
        employeeEntityUpdated.setAddress(employeeEntity.getAddress());
      }
      if(!employeeEntityUpdated.getEmail().equals(employeeEntity.getEmail())){
        employeeEntityUpdated.setEmail(employeeEntity.getEmail());
      }
      if(!employeeEntityUpdated.getPhone().equals(employeeEntity.getPhone())){
        employeeEntityUpdated.setPhone(employeeEntity.getPhone());
      }
      if(employeeEntityUpdated.getPosition() != employeeEntity.getPosition()){
        employeeEntityUpdated.setPosition(employeeEntity.getPosition());
      }
      if(!employeeEntityUpdated.getNote().equals(employeeEntity.getNote())){
        employeeEntityUpdated.setNote(employeeEntity.getNote());
      }
      if(employeeEntityUpdated.isActive() != employeeEntity.isActive()){
        employeeEntityUpdated.setActive(employeeEntity.isActive());
      }
      if(!Objects.equals(employeeEntityUpdated.getDepartment(), employeeEntity.getDepartment())){
        employeeEntityUpdated.setDepartment(employeeEntity.getDepartment());
      }
      repository.save(employeeEntityUpdated);
      return 200;
    }catch (Exception e){
      e.printStackTrace();
      return 400;
    }
  }

  @Override
  public int deleteObject(int id) {
    try{
      EmployeeEntity employeeEntity = repository.findById(id).get();
      employeeEntity.setActive(false);
      repository.save(employeeEntity);
      return 200;
    }catch (Exception e){
      e.printStackTrace();
      return 400;
    }
  }
}
