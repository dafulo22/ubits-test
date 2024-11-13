package com.test.demo.ubits.service;

import com.test.demo.ubits.dto.ModuleDto;
import com.test.demo.ubits.entity.Module;
import com.test.demo.ubits.entity.Submodule;
import com.test.demo.ubits.mapper.ModuleMapper;
import com.test.demo.ubits.mapper.SubmoduleMapper;
import com.test.demo.ubits.repository.ModuleRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModuleService {

  private final ModuleRepository moduleRepository;

  private final ModuleMapper moduleMapper;
  private final SubmoduleMapper subModuleMapper;

  @Autowired
  public ModuleService(ModuleRepository moduleRepository, ModuleMapper moduleMapper,
      SubmoduleMapper subModuleMapper) {
    this.moduleRepository = moduleRepository;
    this.moduleMapper = moduleMapper;
    this.subModuleMapper = subModuleMapper;
  }


  public List<ModuleDto> findAll() {
    List<Module> modules = moduleRepository.findAll();
    return moduleMapper.toDtoList(modules);
  }

  public ModuleDto findById(Long id) {
    Module module = moduleRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Module not found"));
    return moduleMapper.toDto(module);
  }

  public ModuleDto save(ModuleDto moduleDto) {
    Module module = moduleMapper.toEntity(moduleDto);

    // Associate submodules with the module
    if (module.getSubmodules() != null) {
      module.getSubmodules().forEach(submodule -> submodule.setModule(module));
    }

    Module savedModule = moduleRepository.save(module);
    return moduleMapper.toDto(savedModule);
  }

  @Transactional
  public ModuleDto update(Long id, ModuleDto moduleDto) {
    // Get the module from the database
    Module moduleDb = moduleRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Module not found"));

    // Map the DTO module to the entity, but without the submodules (to handle them manually)
    moduleDb.setTitle(moduleDto.getTitle());
    moduleDb.setDescription(moduleDto.getDescription());

    // Update the list of submodules
    if (moduleDto.getSubmodules() != null) {
      // Convert DTO submodules to entities and set up relationships
      List<Submodule> updatedSubmodules = moduleDto.getSubmodules().stream()
          .map(submoduleDto -> {
            if (submoduleDto.getId() != null) {
              // If the submodule has an ID, look it up in the list of existing submodules
              return moduleDb.getSubmodules().stream()
                  .filter(
                      existingSubmodule -> existingSubmodule.getId().equals(submoduleDto.getId()))
                  .findFirst()
                  .map(existingSubmodule -> {
                    // Update existing submodule
                    existingSubmodule.setTitle(submoduleDto.getTitle());
                    existingSubmodule.setSubtitle(submoduleDto.getSubtitle());
                    existingSubmodule.setDescription(submoduleDto.getDescription());
                    existingSubmodule.setButton(submoduleDto.getButton());
                    return existingSubmodule;
                  }).orElseThrow(() -> new EntityNotFoundException(
                      "Submodule not found with id: " + submoduleDto.getId()));
            } else {
              // If the submodule has no ID, create it as new
              Submodule newSubmodule = subModuleMapper.toEntity(submoduleDto);
              newSubmodule.setModule(moduleDb); // Relacionar con el módulo padre
              return newSubmodule;
            }
          })
          .toList();

      // Replace submodules in the main entity
      moduleDb.getSubmodules().addAll(updatedSubmodules);
    }

    // Save the module with its updated submodules
    Module updatedModule = moduleRepository.save(moduleDb);
    return moduleMapper.toDto(updatedModule);
  }

  public boolean delete(Long id) {
    if (moduleRepository.existsById(id)) {
      moduleRepository.deleteById(id);
      return true;
    }
    return false;
  }
}