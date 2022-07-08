package huce.edu.workmanagementprojectbackend.services.task;

import huce.edu.workmanagementprojectbackend.model.ProjectEntity;
import huce.edu.workmanagementprojectbackend.model.TaskEntity;
import huce.edu.workmanagementprojectbackend.paging.Paged;
import huce.edu.workmanagementprojectbackend.paging.Paging;
import huce.edu.workmanagementprojectbackend.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TaskServiceImpl implements ITaskService{

  @Autowired
  private TaskRepository repository;

  @Override
  public List<TaskEntity> getAll() {
    return repository.findAllActive();
  }
  @Override
  public List<TaskEntity> getAllTasksByProjectId(int projectId) {
    return repository.findAllTasksByProjectId(projectId);
  }

  @Override
  public TaskEntity getObjectById(int id) {
    return repository.findById(id).get();
  }

  @Override
  public int insertObject(TaskEntity taskEntity) {
    taskEntity.setCreateDate(new Date());
    taskEntity.setActive(true);
    repository.save(taskEntity);
    return 1;
  }

  @Override
  public int updateObject(TaskEntity taskEntity) {
    try{
      TaskEntity taskEntityUpdated = repository.findById(taskEntity.getId()).get();
      if(!taskEntityUpdated.getTitle().equals(taskEntity.getTitle())){
        taskEntityUpdated.setTitle(taskEntity.getTitle());
      }
      if(!taskEntityUpdated.getContent().equals(taskEntity.getContent())){
        taskEntityUpdated.setContent(taskEntity.getContent());
      }
      if(taskEntityUpdated.getDeadline() != taskEntity.getDeadline()){
        taskEntityUpdated.setDeadline(taskEntity.getDeadline());
      }
      if(taskEntityUpdated.getBeginDate() != taskEntity.getBeginDate()){
        taskEntityUpdated.setBeginDate(taskEntity.getBeginDate());
      }
      if(taskEntityUpdated.getEndDate() != taskEntity.getEndDate()){
        taskEntityUpdated.setEndDate(taskEntity.getEndDate());
      }
      if(!taskEntityUpdated.getNote().equals(taskEntity.getNote())){
        taskEntityUpdated.setNote(taskEntity.getNote());
      }
      if(taskEntityUpdated.getWorkProgressLevel() != taskEntity.getWorkProgressLevel()){
        taskEntityUpdated.setWorkProgressLevel(taskEntity.getWorkProgressLevel());
      }
      taskEntityUpdated.setUpdateDate(new Date());
      repository.save(taskEntityUpdated);
      return 200;
    }catch (Exception e){
      e.printStackTrace();
      return 400;
    }
  }

  @Override
  public int deleteObject(int id) {
    TaskEntity deleteTask = repository.getById(id);
    deleteTask.setActive(false);
    deleteTask = repository.save(deleteTask);
    return deleteTask.getId();
  }

  @Override
  public Paged<TaskEntity> getPage(int pageNumber) {
    return null;
  }

  @Override
  public Paged<TaskEntity> getPageByProjectId(int projectId, int pageNumber){
    Page<TaskEntity> postPage = repository.findAllTasksByProjectId(projectId, PageRequest.of(pageNumber - 1, Paging.PAGE_SIZE));
    return new Paged<>(postPage, Paging.of(postPage.getTotalPages(), pageNumber, Paging.PAGE_SIZE));
  }
}
