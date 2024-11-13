package com.test.demo.ubits.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubmoduleDto {

  private Long id;

  private String title;

  private String subtitle;

  private String description;

  private String button;

}