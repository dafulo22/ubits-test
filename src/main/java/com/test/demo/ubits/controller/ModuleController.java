package com.test.demo.ubits.controller;

import com.test.demo.ubits.dto.ModuleDto;
import com.test.demo.ubits.service.ModuleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/module")
public class ModuleController {

  private final ModuleService moduleService;

  @Autowired
  public ModuleController(ModuleService moduleService) {
    this.moduleService = moduleService;
  }

  @GetMapping
  public ResponseEntity<List<ModuleDto>> getAllModules() {
    List<ModuleDto> modules = moduleService.findAll();
    return ResponseEntity.ok(modules);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ModuleDto> getModuleById(@PathVariable Long id) {
    ModuleDto module = moduleService.findById(id);
    if (module == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(module);
  }

  @PostMapping
  public ResponseEntity<ModuleDto> createModule(@RequestBody ModuleDto moduleDto) {
    ModuleDto createdModule = moduleService.save(moduleDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdModule);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ModuleDto> updateModule(@PathVariable Long id,
      @RequestBody ModuleDto moduleDto) {
    ModuleDto updatedModule = moduleService.update(id, moduleDto);
    if (updatedModule.getId() == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(updatedModule);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteModule(@PathVariable Long id) {
    boolean deleted = moduleService.delete(id);
    if (!deleted) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.noContent().build();
  }
}