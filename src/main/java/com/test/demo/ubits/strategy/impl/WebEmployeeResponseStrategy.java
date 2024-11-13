package com.test.demo.ubits.strategy.impl;

import com.test.demo.ubits.dto.EmployeeDto;
import com.test.demo.ubits.strategy.EmployeeResponseStrategy;
import com.test.demo.ubits.strategy.StrategyType;
import org.springframework.stereotype.Service;

@Service
public class WebEmployeeResponseStrategy implements EmployeeResponseStrategy {

  @Override
  public StrategyType getType() {
    return StrategyType.WEB;
  }

  @Override
  public EmployeeDto getResponse(EmployeeDto dto) {
    dto.setDepartment("Web");
    dto.setPosition("Developer");
    return dto;
  }
}
