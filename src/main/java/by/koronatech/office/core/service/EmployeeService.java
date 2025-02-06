package by.koronatech.office.core.service;

import by.koronatech.office.core.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee findById(long id);

    List<Employee> findAllEmployeesByDepartment(String department);

    Employee makeEmployeeManager(long id);

    Employee save(Employee employee);

    void delete(long id);

    List<Employee> findAll();

    boolean existsById(long id);

}
