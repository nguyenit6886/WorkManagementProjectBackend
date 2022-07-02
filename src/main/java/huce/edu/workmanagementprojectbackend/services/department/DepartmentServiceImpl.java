package huce.edu.workmanagementprojectbackend.services.department;

import huce.edu.workmanagementprojectbackend.model.DepartmentEntity;
import huce.edu.workmanagementprojectbackend.paging.Paged;
import huce.edu.workmanagementprojectbackend.paging.Paging;
import huce.edu.workmanagementprojectbackend.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
      e.printStackTrace();
      return 400;
    }
  }

  @Override
  public int updateObject(DepartmentEntity departmentEntity) {
    try{
      DepartmentEntity departmentEntityUpdated = repository.findById(departmentEntity.getId()).get();
      if (departmentEntityUpdated.getUpdateDate() != departmentEntity.getUpdateDate())
        departmentEntityUpdated.setUpdateDate(departmentEntity.getUpdateDate());
      if (!departmentEntityUpdated.getName().equals(departmentEntity.getName()))
        departmentEntityUpdated.setName(departmentEntity.getName());
      if (!departmentEntityUpdated.getNote().equals(departmentEntity.getNote()))
        departmentEntityUpdated.setNote(departmentEntity.getNote());
      repository.save(departmentEntityUpdated);
      return 200;
    }catch (Exception e){
      e.printStackTrace();
      return 400;
    }
  }

  @Override
  public int deleteObject(int id) {
    try{
      DepartmentEntity departmentEntityActived = repository.findById(id).get();
      departmentEntityActived.setActive(false);
      repository.save(departmentEntityActived);
      return 200;
    }catch (Exception e){
      e.printStackTrace();
      return 400;
    }
  }

  @Override
  public Paged<DepartmentEntity> getPage(int pageNumber) {
    Page<DepartmentEntity> postPage = repository.findAllActive(PageRequest.of(pageNumber - 1, Paging.PAGE_SIZE));
    return new Paged<>(postPage, Paging.of(postPage.getTotalPages(), pageNumber, Paging.PAGE_SIZE));
  }
}
