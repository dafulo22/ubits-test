package com.test.demo.ubits.service;

import com.test.demo.ubits.dto.EmployeeDto;
import com.test.demo.ubits.entity.Employee;
import com.test.demo.ubits.mapper.EmployeeMapper;
import com.test.demo.ubits.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeFacade {

  private final EmployeeRepository repository;
  private final EmployeeMapper mapper;

  @Autowired
  public EmployeeFacade(EmployeeRepository repository, EmployeeMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  public EmployeeDto findById(Long id) {
    Employee employee = repository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
    return mapper.toDto(employee);
  }

  public List<EmployeeDto> findAll() {
    return mapper.toDtoList(repository.findAll());
  }

  public EmployeeDto save(EmployeeDto employeeDto) {
    Employee employee = mapper.toEntity(employeeDto);
    Employee savedEmployee = repository.save(employee);
    return mapper.toDto(savedEmployee);
  }
}