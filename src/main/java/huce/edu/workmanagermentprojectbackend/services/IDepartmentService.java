package huce.edu.workmanagermentprojectbackend.services;

import huce.edu.workmanagermentprojectbackend.model.DepartmentEntity;

import java.util.List;

public interface IDepartmentService {
  List<DepartmentEntity> getDepartments();
}
