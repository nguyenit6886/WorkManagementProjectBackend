package huce.edu.workmanagementprojectbackend.services.project;

import huce.edu.workmanagementprojectbackend.model.ProjectEntity;
import huce.edu.workmanagementprojectbackend.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements IProjectService{

  @Autowired
  private ProjectRepository repository;

  @Override
  public List<ProjectEntity> getAll() {
    return repository.findAll();
  }

  @Override
  public ProjectEntity getObjectById(int id) {
    return repository.findById(id).get();
  }

  @Override
  public int insertObject(ProjectEntity projectEntity) {
    return 0;
  }
}
