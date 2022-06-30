package huce.edu.workmanagementprojectbackend.services.comment;

import huce.edu.workmanagementprojectbackend.model.CommentEntity;
import huce.edu.workmanagementprojectbackend.model.WorkProgressEntity;
import huce.edu.workmanagementprojectbackend.repository.CommentReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements ICommentService{

  @Autowired
  private CommentReponsitory repository;

  @Override
  public List<CommentEntity> getAll() {
    return repository.findAllActive();
  }

  @Override
  public CommentEntity getObjectById(int id) {
    return repository.findById(id).get();
  }

  @Override
  public int insertObject(CommentEntity commentEntity) {
    try{
      repository.save(commentEntity);
      return 200;
    }catch (Exception e){
      e.printStackTrace();
      return 400;
    }
  }

  @Override
  public int updateObject(CommentEntity commentEntity) {
    return 0;
  }

  @Override
  public int deleteObject(int id) {
    return 0;
  }

  @Override
  public List<CommentEntity> getCommentByWorkProgress(WorkProgressEntity workProgress) {
    return repository.findAllActive().stream().filter(c -> c.getWorkProgress().equals(workProgress)).collect(Collectors.toList());
  }
}
