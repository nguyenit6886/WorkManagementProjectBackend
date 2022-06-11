package huce.edu.workmanagementprojectbackend.services.comment;

import huce.edu.workmanagementprojectbackend.model.CommentEntity;
import huce.edu.workmanagementprojectbackend.repository.CommentReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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

  @Override
  public int insertObject(CommentEntity commentEntity) {
    return 0;
  }

  @Override
  public int updateObject(CommentEntity commentEntity) {
    return 0;
  }

  @Override
  public int deleteObject(int id) {
    return 0;
  }
}
