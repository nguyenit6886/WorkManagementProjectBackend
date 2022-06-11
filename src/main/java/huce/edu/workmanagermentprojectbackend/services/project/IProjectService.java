package huce.edu.workmanagermentprojectbackend.services.project;


import huce.edu.workmanagermentprojectbackend.model.ProjectEntity;
import huce.edu.workmanagermentprojectbackend.services.IFunctionService;

import java.util.List;

public interface IProjectService extends IFunctionService<ProjectEntity> {
  @Override
  List getAll();

  @Override
  ProjectEntity getObjectById(int id);
}
