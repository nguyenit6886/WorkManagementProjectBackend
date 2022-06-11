package huce.edu.workmanagementprojectbackend.services.workprogress;

import huce.edu.workmanagementprojectbackend.model.WorkProgressEntity;
import huce.edu.workmanagementprojectbackend.repository.WorkProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkProgressServiceImpl implements IWorkProgressService{

  @Autowired
  private WorkProgressRepository repository;

  @Override
  public List<WorkProgressEntity> getAll() {
    return repository.findAll();
  }

  @Override
  public WorkProgressEntity getObjectById(int id) {
    return repository.findById(id).get();
  }

  @Override
  public int insertObject(WorkProgressEntity workProgressEntity) {
    return 0;
  }

  @Override
  public int updateObject(WorkProgressEntity workProgressEntity) {
    return 0;
  }

  @Override
  public int deleteObject(int id) {
    return 0;
  }
}
