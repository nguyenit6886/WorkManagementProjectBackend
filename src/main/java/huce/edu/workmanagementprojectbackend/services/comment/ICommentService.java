package huce.edu.workmanagementprojectbackend.services.comment;

import huce.edu.workmanagementprojectbackend.model.CommentEntity;
import huce.edu.workmanagementprojectbackend.services.IFunctionService;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ICommentService extends IFunctionService<CommentEntity> {
  @Override
  List<CommentEntity> getAll();

  @Override
  CommentEntity getObjectById(int id);

  @Override
  int insertObject(CommentEntity commentEntity);

  @Override
  int updateObject(CommentEntity commentEntity);

  @Override
  int deleteObject(int id);
}
