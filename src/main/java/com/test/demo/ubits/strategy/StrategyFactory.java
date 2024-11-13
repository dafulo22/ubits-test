package com.test.demo.ubits.strategy;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class StrategyFactory {

  private final Map<StrategyType, EmployeeResponseStrategy> strategies = new EnumMap<>(
      StrategyType.class);

  public StrategyFactory(Set<EmployeeResponseStrategy> employeeResponseStrategies) {
    createStrategy(employeeResponseStrategies);
  }

  public EmployeeResponseStrategy findStrategy(StrategyType strategyType) {
    return strategies.get(strategyType);
  }

  private void createStrategy(Set<EmployeeResponseStrategy> responseStrategies) {
    responseStrategies.forEach(
        strategy -> strategies.put(strategy.getType(), strategy));
  }
}