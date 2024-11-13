package com.test.demo.ubits.dto;

import java.util.Set;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ModuleDto {

  private Long id;

  private String title;

  private String description;

  private Set<SubmoduleDto> submodules;

}