package huce.edu.workmanagementprojectbackend.services.workprogress;

import huce.edu.workmanagementprojectbackend.model.DepartmentEntity;
import huce.edu.workmanagementprojectbackend.model.TaskEntity;
import huce.edu.workmanagementprojectbackend.model.WorkProgressEntity;
import huce.edu.workmanagementprojectbackend.paging.Paged;
import huce.edu.workmanagementprojectbackend.paging.Paging;
import huce.edu.workmanagementprojectbackend.repository.TaskRepository;
import huce.edu.workmanagementprojectbackend.repository.WorkProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkProgressServiceImpl implements IWorkProgressService{

  @Autowired
  private WorkProgressRepository repository;

  @Autowired
  private TaskRepository taskRepository;

  @Override
  public List<WorkProgressEntity> getAll() {
    return repository.findAllActive();
  }

  @Override
  public WorkProgressEntity getObjectById(int id) {
    return repository.findById(id).get();
  }

  @Override
  public int insertObject(WorkProgressEntity workProgressEntity) {
    try{
      return repository.save(workProgressEntity).getId();
    }catch (Exception e){
      e.printStackTrace();
      return 400;
    }
  }

  @Override
  public int updateObject(WorkProgressEntity workProgressEntity) {
    try{
      WorkProgressEntity departmentEntityUpdated = repository.findById(workProgressEntity.getId()).get();
      if (departmentEntityUpdated.getUpdateDate() != workProgressEntity.getUpdateDate())
        departmentEntityUpdated.setUpdateDate(workProgressEntity.getUpdateDate());
      if (departmentEntityUpdated.getWorkProgressLevel() != workProgressEntity.getWorkProgressLevel())
        departmentEntityUpdated.setWorkProgressLevel(workProgressEntity.getWorkProgressLevel());
      if (departmentEntityUpdated.getTitle() != workProgressEntity.getTitle())
        departmentEntityUpdated.setTitle(workProgressEntity.getTitle());
      if (departmentEntityUpdated.getContent() != workProgressEntity.getContent())
        departmentEntityUpdated.setContent(workProgressEntity.getContent());
      if (departmentEntityUpdated.getFileName() != workProgressEntity.getFileName())
        departmentEntityUpdated.setFileName(workProgressEntity.getFileName());
      return repository.save(departmentEntityUpdated).getId();
    }catch (Exception e){
      e.printStackTrace();
      return 400;
    }
  }

  @Override
  public int deleteObject(int id) {
    try{
      WorkProgressEntity workProgressEntityUpdated = repository.findById(id).get();
      workProgressEntityUpdated.setActive(false);
      repository.save(workProgressEntityUpdated);
      return 200;
    }catch (Exception e){
      e.printStackTrace();
      return 400;
    }
  }

  @Override
  public Paged<WorkProgressEntity> getPage(int pageNumber) {
    return null;
  }

  @Override
  public int insertFile(MultipartFile[] multipartFiles, WorkProgressEntity workProgress) {
    try{
      Path currentPath = Paths.get(".");
      Path absolutePath = currentPath.toAbsolutePath();
      String imagePath = absolutePath + "/src/main/webapp/static/upload/" + workProgress.getId();
      Path path = Paths.get(imagePath);
      if(!java.nio.file.Files.exists(path)){
        java.nio.file.Files.createDirectories(path);
      }
      for(MultipartFile multipartFile : multipartFiles){
        path = Paths.get(imagePath + "/" + multipartFile.getOriginalFilename());
        byte[] bytes = multipartFile.getBytes();
        java.nio.file.Files.write(path,bytes);
      }
      return 200;
    }catch (Exception e){
      e.printStackTrace();
      return 400;
    }
  }

  @Override
  public Paged<WorkProgressEntity> getPageByTaskId(int taskId, int pageNumber){
    Page<WorkProgressEntity> postPage = repository.findAllWorkProgressByProjectId(taskId, PageRequest.of(pageNumber - 1, Paging.PAGE_SIZE));
    return new Paged<>(postPage, Paging.of(postPage.getTotalPages(), pageNumber, Paging.PAGE_SIZE));
  }

  @Override
  public List<WorkProgressEntity> getObjectsByTask(int taskId) {
    TaskEntity taskEntity = taskRepository.findById(taskId).get();
    return repository.findAllActive().stream()
        .filter(w -> w.getTask().equals(taskEntity)).collect(Collectors.toList());
  }
}
