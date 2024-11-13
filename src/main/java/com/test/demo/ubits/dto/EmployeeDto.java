package com.test.demo.ubits.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeDto {

  private Long id;

  @NotBlank(message = "El nombre no puede estar en blanco")
  @Size(max = 50, message = "El nombre no puede tener más de 50 caracteres")
  private String name;

  @NotBlank(message = "La posición no puede estar en blanco")
  @Size(max = 30, message = "La posición no puede tener más de 30 caracteres")
  private String position;

  private String department;

  @Email(message = "Debe ser un correo electrónico válido")
  @NotBlank(message = "El correo no puede estar en blanco")
  private String email;
}
