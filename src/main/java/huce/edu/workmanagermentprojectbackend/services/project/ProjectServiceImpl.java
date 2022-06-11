package huce.edu.workmanagermentprojectbackend.services.project;

import huce.edu.workmanagermentprojectbackend.model.ProjectEntity;
import huce.edu.workmanagermentprojectbackend.repository.ProjectRepository;
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
}
