package by.koronatech.office.api.controller;

import by.koronatech.office.api.dto.CreateEmployeeDTO;
import by.koronatech.office.api.dto.GetEmployeeDTO;
import by.koronatech.office.core.exaption.employee.EmployeeNotFoundException;
import by.koronatech.office.core.mapper.employee.CreateEmployeeMapper;
import by.koronatech.office.core.mapper.employee.GetEmployeeMapper;
import by.koronatech.office.core.model.Employee;
import by.koronatech.office.core.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
        if (employeeService.existsById(id)) {
            Employee employee = createEmployeeMapper.merge(employeeService.findById(id), createEmployeeDTO);
            return getEmployeeMapper.toDto(employeeService.save(employee));
        } else {
            throw new EmployeeNotFoundException(id);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable long id) {
        if (employeeService.existsById(id)) {
            employeeService.delete(id);
        } else {
            throw new EmployeeNotFoundException(id);
        }
    }
}
