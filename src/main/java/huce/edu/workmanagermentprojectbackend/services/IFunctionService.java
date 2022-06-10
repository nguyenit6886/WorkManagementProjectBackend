package huce.edu.workmanagermentprojectbackend.services;

import java.util.List;

public interface IFunctionService<E> {
  List<E> getAll();

  E getObjectById(int id);
}
