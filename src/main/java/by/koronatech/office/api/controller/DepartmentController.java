package by.koronatech.office.api.controller;

import by.koronatech.office.api.dto.department.GetDepartmentDTO;
import by.koronatech.office.core.mapper.department.GetDepartmentMapper;
import by.koronatech.office.core.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/department")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;
    private final GetDepartmentMapper getDepartmentMapper;

    @GetMapping
    public List<GetDepartmentDTO> getAll() {
        return getDepartmentMapper.toDtos(departmentService.findAll());
    }
}
