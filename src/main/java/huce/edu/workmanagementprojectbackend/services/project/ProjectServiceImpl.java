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
    return repository.findAllActive();
  }

  @Override
  public ProjectEntity getObjectById(int id) {
    return repository.findById(id).get();
  }

  @Override
  public int insertObject(ProjectEntity projectEntity) {
    try{
      repository.save(projectEntity);
      return 200;
    }catch (Exception e){
      e.printStackTrace();
      return 400;
    }
  }

  @Override
  public int updateObject(ProjectEntity projectEntity) {
    try{
      ProjectEntity projectEntityUpdated = repository.findById(projectEntity.getId()).get();
      if(!projectEntityUpdated.getTitle().equals(projectEntity.getTitle())){
        projectEntityUpdated.setTitle(projectEntity.getTitle());
      }
      if(!projectEntityUpdated.getContent().equals(projectEntity.getContent())){
        projectEntityUpdated.setContent(projectEntity.getContent());
      }
      if(projectEntityUpdated.getDeadline() != projectEntity.getDeadline()){
        projectEntityUpdated.setDeadline(projectEntity.getDeadline());
      }
      if(projectEntityUpdated.getBeginDate() != projectEntity.getBeginDate()){
        projectEntityUpdated.setBeginDate(projectEntity.getBeginDate());
      }
      if(projectEntityUpdated.getEndDate() != projectEntity.getEndDate()){
        projectEntityUpdated.setEndDate(projectEntity.getEndDate());
      }
      if(!projectEntityUpdated.getNote().equals(projectEntity.getNote())){
        projectEntityUpdated.setNote(projectEntity.getNote());
      }
      if(projectEntityUpdated.isActive() != projectEntity.isActive()){
        projectEntityUpdated.setActive(projectEntity.isActive());
      }
      repository.save(projectEntityUpdated);
      return 200;
    }catch (Exception e){
      e.printStackTrace();
      return 400;
    }
  }

  @Override
  public int deleteObject(int id) {
    try{
      ProjectEntity projectEntity = repository.findById(id).get();
      projectEntity.setActive(false);
      repository.save(projectEntity);
      return 200;
    }catch (Exception e){
      e.printStackTrace();
      return 400;
    }
  }
}
