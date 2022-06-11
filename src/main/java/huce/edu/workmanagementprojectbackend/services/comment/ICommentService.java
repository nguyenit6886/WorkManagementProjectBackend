package huce.edu.workmanagementprojectbackend.services.comment;

import huce.edu.workmanagementprojectbackend.model.CommentEntity;
import huce.edu.workmanagementprojectbackend.services.IFunctionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICommentService extends IFunctionService<CommentEntity> {
  @Override
  List<CommentEntity> getAll();

  @Override
  CommentEntity getObjectById(int id);
}
