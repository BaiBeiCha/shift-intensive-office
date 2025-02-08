package by.koronatech.office.api.controller;

import by.koronatech.office.api.dto.employee.CreateEmployeeDTO;
import by.koronatech.office.api.dto.employee.GetEmployeeDTO;
import by.koronatech.office.core.mapper.employee.CreateEmployeeMapper;
import by.koronatech.office.core.mapper.employee.GetEmployeeMapper;
import by.koronatech.office.core.model.Employee;
import by.koronatech.office.core.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final GetEmployeeMapper getEmployeeMapper;
    private final CreateEmployeeMapper createEmployeeMapper;

    @GetMapping
    public List<GetEmployeeDTO> getAllEmployees() {
        return getEmployeeMapper.toDtos(employeeService.findAll());
    }

    @GetMapping(params = "department")
    public List<GetEmployeeDTO> getEmployees(@RequestParam("department") String department) {
        return getEmployeeMapper.toDtos(employeeService.findAllEmployeesByDepartment(department));
    }

    @PostMapping
    public GetEmployeeDTO createEmployee(@RequestBody CreateEmployeeDTO createEmployeeDTO) {
        Employee employee = createEmployeeMapper.toEntity(createEmployeeDTO);
        return getEmployeeMapper.toDto(employeeService.save(employee));
    }

    @PatchMapping("/{id}")
    public GetEmployeeDTO makeEmployeeManager(@PathVariable long id) {
        return getEmployeeMapper.toDto(employeeService.makeEmployeeManager(id));
    }

    @PutMapping("/{id}")
    public GetEmployeeDTO changeEmployee(@PathVariable long id,
                                         @RequestBody CreateEmployeeDTO createEmployeeDTO) {
        Employee employee = createEmployeeMapper.merge(employeeService.findById(id), createEmployeeDTO);
        return getEmployeeMapper.toDto(employeeService.save(employee));
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable long id) {
        employeeService.delete(id);
    }
}
