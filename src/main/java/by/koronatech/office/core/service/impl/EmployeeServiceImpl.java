package by.koronatech.office.core.service.impl;

import by.koronatech.office.core.exaption.department.DepartmentNotExistsException;
import by.koronatech.office.core.exaption.employee.EmployeeNotFoundException;
import by.koronatech.office.core.model.Employee;
import by.koronatech.office.core.service.DepartmentService;
import by.koronatech.office.core.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final DepartmentService departmentService;

    private final List<Employee> employeeRepository;
    private long lastId;

    public EmployeeServiceImpl(DepartmentService departmentService) {
        this.departmentService = departmentService;

        this.employeeRepository = new ArrayList<>();

        employeeRepository.add(Employee.builder()
                .id(0L)
                .name("Иван Иванов")
                .salary(1200d)
                .department("Бухгалтерия")
                .manager(false)
                .build());

        employeeRepository.add(Employee.builder()
                .id(1L)
                .name("Олег Петрович")
                .salary(1500d)
                .department("Отдел продаж")
                .manager(true)
                .build());

        employeeRepository.add(Employee.builder()
                .id(2L)
                .name("Владимир Дмитриевич")
                .salary(1800d)
                .department("Отдел кадров")
                .manager(false)
                .build());

        this.lastId = employeeRepository.get(employeeRepository.size() - 1).getId();
    }

    @Override
    public Employee findById(long id) {
        return employeeRepository.stream()
                .filter(employee -> employee.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Employee> findAllEmployeesByDepartment(String department) {
        if (!departmentService.existsByName(department)) {
            throw new DepartmentNotExistsException(department);
        }

        return employeeRepository.stream()
                .filter(employee -> employee.getDepartment().equals(department))
                .toList();
    }

    @Override
    public Employee makeEmployeeManager(long id) {
        Employee employee = findById(id);

        if (employee == null) {
            throw new EmployeeNotFoundException(id);
        }

        employee.setManager(true);
        return save(employee);
    }

    @Override
    public Employee save(Employee employee) {
        if (!departmentService.existsByName(employee.getDepartment())) {
            throw new DepartmentNotExistsException(employee.getDepartment());
        }

        Long id = employee.getId();

        if (id == null) {
            return saveIfNotFound(employee);
        }

        Employee existingEmployee = findById(id);

        if (existingEmployee != null) {
            return saveIfFound(employee, existingEmployee);
        } else {
            return saveIfNotFound(employee);
        }
    }

    private Employee saveIfNotFound(Employee employee) {
        employee.setId(++lastId);
        employeeRepository.add(employee);
        return employee;
    }

    private Employee saveIfFound(Employee employee, Employee existingEmployee) {
        int index = employeeRepository.indexOf(existingEmployee);
        employeeRepository.set(index, employee);
        return employee;
    }

    @Override
    public void delete(long id) {
        Employee employee = findById(id);

        if (employee != null) {
            employeeRepository.remove(employee);
        } else {
            throw new EmployeeNotFoundException(id);
        }
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository;
    }

    @Override
    public boolean existsById(long id) {
        for (Employee employee : employeeRepository) {
            if (employee.getId() == id) {
                return true;
            }
        }

        return false;
    }
}
