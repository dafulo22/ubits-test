package com.test.demo.ubits.mapper;

import com.test.demo.ubits.dto.ModuleDto;
import com.test.demo.ubits.entity.Module;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = SubmoduleMapper.class)
public interface ModuleMapper {

  ModuleMapper INSTANCE = Mappers.getMapper(ModuleMapper.class);

  ModuleDto toDto(Module module);

  Module toEntity(ModuleDto moduleDto);

  List<ModuleDto> toDtoList(List<Module> modules);

  List<Module> toEntityList(List<ModuleDto> modulesDto);
}