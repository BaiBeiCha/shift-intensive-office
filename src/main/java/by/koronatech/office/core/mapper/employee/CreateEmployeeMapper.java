package by.koronatech.office.core.mapper.employee;

import by.koronatech.office.api.dto.employee.CreateEmployeeDTO;
import by.koronatech.office.core.mapper.BaseMapper;
import by.koronatech.office.core.model.Employee;
import by.koronatech.office.core.service.DepartmentService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(config = BaseMapper.class, uses = DepartmentService.class)
public abstract class CreateEmployeeMapper implements BaseMapper<Employee, CreateEmployeeDTO> {

    protected DepartmentService departmentService;

    @Mapping(source = "department.name", target = "department")
    public abstract CreateEmployeeDTO toDto(Employee entity);

    @Mapping(target = "department", expression = "java(departmentService.findByName(dto.getDepartment()))")
    public abstract Employee toEntity(CreateEmployeeDTO dto);

    public abstract List<CreateEmployeeDTO> toDtos(List<Employee> entities);

    public abstract List<Employee> toEntities(List<CreateEmployeeDTO> dtos);

    @Mapping(target = "department", expression = "java(departmentService.findByName(dto.getDepartment()))")
    public abstract Employee merge(@MappingTarget Employee entity, CreateEmployeeDTO dto);
}
