package by.koronatech.office.core.service;

import by.koronatech.office.core.model.Department;

import java.util.List;

public interface DepartmentService {

    List<Department> findAll();

    boolean existsByName(String name);

    Department findByName(String name);

}
