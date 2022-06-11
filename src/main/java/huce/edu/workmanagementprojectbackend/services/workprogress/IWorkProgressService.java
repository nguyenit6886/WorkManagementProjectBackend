package huce.edu.workmanagementprojectbackend.services.workprogress;

import huce.edu.workmanagementprojectbackend.model.WorkProgressEntity;
import huce.edu.workmanagementprojectbackend.services.IFunctionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IWorkProgressService extends IFunctionService<WorkProgressEntity> {
  @Override
  List<WorkProgressEntity> getAll();

  @Override
  WorkProgressEntity getObjectById(int id);
}
