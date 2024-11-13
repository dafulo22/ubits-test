package com.test.demo.ubits.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CatalogDto {

  private Long id;
  private String title;
  private String partnerName;
  private String categoryName;
  private Long duration;
  private String levelName;
  private String description;

}