package com.test.demo.ubits.strategy.impl;

import com.test.demo.ubits.dto.EmployeeDto;
import com.test.demo.ubits.strategy.EmployeeResponseStrategy;
import com.test.demo.ubits.strategy.StrategyType;
import org.springframework.stereotype.Service;

@Service
public class MobileEmployeeResponseStrategy implements EmployeeResponseStrategy {

  @Override
  public StrategyType getType() {
    return StrategyType.MOBILE;
  }

  @Override
  public EmployeeDto getResponse(EmployeeDto dto) {
    dto.setDepartment("Mobile");
    dto.setPosition("Tester");
    return dto;
  }
}
