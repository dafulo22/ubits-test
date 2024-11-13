package com.test.demo.ubits.controller;

import com.test.demo.ubits.dto.EmployeeDto;
import com.test.demo.ubits.service.EmployeeFacade;
import com.test.demo.ubits.strategy.StrategyFactory;
import com.test.demo.ubits.strategy.StrategyType;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

  private final EmployeeFacade employeeFacade;
  private final StrategyFactory strategyFactory;

  @Autowired
  public EmployeeController(EmployeeFacade employeeFacade, StrategyFactory strategyFactory) {
    this.employeeFacade = employeeFacade;
    this.strategyFactory = strategyFactory;
  }

  @GetMapping
  public ResponseEntity<List<EmployeeDto>> getEmployees() {
    List<EmployeeDto> employeesDto = employeeFacade.findAll();
    return ResponseEntity.ok(employeesDto);
  }

  @GetMapping("/{id}")
  public EmployeeDto getEmployeeById(@PathVariable Long id, @RequestParam StrategyType channel) {
    var dto = employeeFacade.findById(id);
    return strategyFactory.findStrategy(channel).getResponse(dto);
  }

  @PostMapping
  public EmployeeDto createEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
    return employeeFacade.save(employeeDto);
  }
}