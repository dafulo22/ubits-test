package com.test.demo.ubits.strategy;

import com.test.demo.ubits.dto.EmployeeDto;

public interface EmployeeResponseStrategy {

  StrategyType getType();

  EmployeeDto getResponse(EmployeeDto dto);
}