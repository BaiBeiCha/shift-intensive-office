package by.koronatech.office.core.service.impl;

import by.koronatech.office.core.exception.department.DepartmentNotExistsException;
import by.koronatech.office.core.exception.employee.EmployeeNotFoundException;
import by.koronatech.office.core.model.Employee;
import by.koronatech.office.core.repository.EmployeesRepository;
import by.koronatech.office.core.service.DepartmentService;
import by.koronatech.office.core.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final DepartmentService departmentService;
    private final EmployeesRepository employeeRepository;

    @Override
    public Employee findById(long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @Override
    public List<Employee> findAllEmployeesByDepartment(String department) {
        if (!departmentService.existsByName(department)) {
            throw new DepartmentNotExistsException(department);
        }

        return employeeRepository.findAllByDepartment(departmentService.findByName(department));
    }

    @Override
    public Employee makeEmployeeManager(long id) {
        Employee employee = findById(id);

        if (employee.getIsManager()) {
            log.info("Employee with id {} already is manager", id);
            return employee;
        }

        employee.setIsManager(true);
        return save(employee);
    }

    @Override
    public Employee save(Employee employee) {
        if (!departmentService.existsByName(employee.getDepartment().getName())) {
            throw new DepartmentNotExistsException(employee.getDepartment().getName());
        }

        return employeeRepository.save(employee);
    }

    @Override
    public void delete(long id) {
        employeeRepository.delete(findById(id));
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public boolean existsById(long id) {
        return employeeRepository.existsById(id);
    }
}
