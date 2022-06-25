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
    return repository.findAllActive();
  }

  @Override
  public DepartmentEntity getObjectById(int id) {
    return repository.findById(id).get();
  }

  @Override
  public int insertObject(DepartmentEntity departmentEntity) {
    try{
      repository.save(departmentEntity);
      return 200;
    }catch (Exception e){
      return 400;
    }
  }

  @Override
  public int updateObject(DepartmentEntity departmentEntity) {
    try{
      DepartmentEntity departmentEntityUpdated = repository.findById(departmentEntity.getId()).get();
      if (departmentEntityUpdated.getUpdateDate() != departmentEntity.getUpdateDate())
        departmentEntityUpdated.setUpdateDate(departmentEntity.getUpdateDate());
      if (departmentEntityUpdated.getName() != departmentEntity.getName())
        departmentEntityUpdated.setName(departmentEntity.getName());
      if (departmentEntityUpdated.getNote() != departmentEntity.getNote())
        departmentEntityUpdated.setNote(departmentEntity.getNote());
      if (departmentEntityUpdated.isActive() != departmentEntity.isActive())
        departmentEntityUpdated.setActive(departmentEntity.isActive());
      repository.save(departmentEntityUpdated);
      return 200;
    }catch (Exception e){
      return 400;
    }
  }

  @Override
  public int deleteObject(int id) {
    try{
      DepartmentEntity departmentEntityActived = repository.findById(id).get();
      departmentEntityActived.setActive(true);
      repository.save(departmentEntityActived);
      return 200;
    }catch (Exception e){
      return 400;
    }
  }
}
