package by.koronatech.office.core.service.impl;

import by.koronatech.office.core.exception.department.DepartmentNotExistsException;
import by.koronatech.office.core.model.Department;
import by.koronatech.office.core.repository.DepartmentsRepository;
import by.koronatech.office.core.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentsRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentsRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public boolean existsByName(String name) {
        return departmentRepository.existsByName(name);
    }

    @Override
    public Department findByName(String name) {
        Optional<Department> optionalDepartment = departmentRepository.findByName(name);

        if (optionalDepartment.isPresent()) {
            return optionalDepartment.get();
        } else {
            throw new DepartmentNotExistsException(name);
        }
    }
}
