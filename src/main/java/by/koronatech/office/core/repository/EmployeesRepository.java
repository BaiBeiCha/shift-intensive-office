package by.koronatech.office.core.repository;

import by.koronatech.office.core.model.Department;
import by.koronatech.office.core.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeesRepository extends JpaRepository<Employee, Long> {

    List<Employee> findAllByDepartment(Department department);

}
