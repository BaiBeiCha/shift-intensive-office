package by.koronatech.office.core.service.impl;

import by.koronatech.office.core.model.Department;
import by.koronatech.office.core.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final List<Department> departmentRepository;

    public DepartmentServiceImpl() {
        this.departmentRepository = List.of(
                new Department(0L, "Бухгалтерия"),
                new Department(1L, "Отдел продаж"),
                new Department(2L, "Отдел маркетинга"),
                new Department(3L, "Отдел кадров"),
                new Department(4L, "Отдел технической поддержки"),
                new Department(5L, "IT отдел")
        );
    }

    @Override
    public List<Department> findAll() {
        return departmentRepository;
    }

    @Override
    public boolean existsByName(String name) {
        return departmentRepository.stream().anyMatch(d -> d.getName().equals(name));
    }
}
