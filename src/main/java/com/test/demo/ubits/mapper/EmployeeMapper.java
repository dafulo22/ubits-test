package com.test.demo.ubits.mapper;

import com.test.demo.ubits.dto.EmployeeDto;
import com.test.demo.ubits.entity.Employee;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

  EmployeeDto toDto(Employee employee);

  Employee toEntity(EmployeeDto employeeDto);

  List<EmployeeDto> toDtoList(List<Employee> employees);

  List<Employee> toEntityList(List<EmployeeDto> employeesDto);
}