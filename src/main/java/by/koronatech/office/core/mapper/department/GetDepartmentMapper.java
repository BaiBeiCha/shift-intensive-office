package by.koronatech.office.core.mapper.department;

import by.koronatech.office.api.dto.GetDepartmentDTO;
import by.koronatech.office.core.mapper.BaseMapper;
import by.koronatech.office.core.model.Department;
import org.mapstruct.Mapper;

@Mapper(config = BaseMapper.class)
public interface GetDepartmentMapper extends BaseMapper<Department, GetDepartmentDTO> {
}
