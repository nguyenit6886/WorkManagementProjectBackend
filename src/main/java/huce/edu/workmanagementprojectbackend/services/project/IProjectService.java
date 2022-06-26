package huce.edu.workmanagementprojectbackend.services.project;


import huce.edu.workmanagementprojectbackend.model.ProjectEntity;
import huce.edu.workmanagementprojectbackend.services.IFunctionService;

import java.util.List;

public interface IProjectService extends IFunctionService<ProjectEntity> {
  @Override
  List<ProjectEntity> getAll();

  @Override
  ProjectEntity getObjectById(int id);

  @Override
  int insertObject(ProjectEntity projectEntity);

  @Override
  int updateObject(ProjectEntity projectEntity);

  @Override
  int deleteObject(int id);
}
