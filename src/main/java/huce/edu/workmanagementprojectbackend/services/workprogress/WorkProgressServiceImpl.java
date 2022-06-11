package huce.edu.workmanagementprojectbackend.services.workprogress;

import huce.edu.workmanagementprojectbackend.model.WorkProgressEntity;
import huce.edu.workmanagementprojectbackend.repository.WorkProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
}
