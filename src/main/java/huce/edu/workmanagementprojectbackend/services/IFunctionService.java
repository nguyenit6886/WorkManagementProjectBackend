package huce.edu.workmanagementprojectbackend.services;

import java.util.List;

public interface IFunctionService<E> {
  List<E> getAll();

  E getObjectById(int id);

  int insertObject(E e);
}
