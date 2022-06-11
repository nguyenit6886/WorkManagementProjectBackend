package huce.edu.workmanagementprojectbackend.services.project;


import huce.edu.workmanagementprojectbackend.model.ProjectEntity;
import huce.edu.workmanagementprojectbackend.services.IFunctionService;

import java.util.List;

public interface IProjectService extends IFunctionService<ProjectEntity> {
  @Override
  List getAll();

  @Override
  ProjectEntity getObjectById(int id);
}