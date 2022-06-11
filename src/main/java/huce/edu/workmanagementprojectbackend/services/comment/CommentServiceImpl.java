package huce.edu.workmanagementprojectbackend.services.comment;

import huce.edu.workmanagementprojectbackend.model.CommentEntity;
import huce.edu.workmanagementprojectbackend.repository.CommentReponsitory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CommentServiceImpl implements ICommentService{

  @Autowired
  private CommentReponsitory reponsitory;

  @Override
  public List<CommentEntity> getAll() {
    return reponsitory.findAll();
  }

  @Override
  public CommentEntity getObjectById(int id) {
    return reponsitory.findById(id).get();
  }
}
