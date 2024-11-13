package com.test.demo.ubits.mapper;

import com.test.demo.ubits.dto.SubmoduleDto;
import com.test.demo.ubits.entity.Submodule;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SubmoduleMapper {

  SubmoduleMapper INSTANCE = Mappers.getMapper(SubmoduleMapper.class);

  SubmoduleDto toDto(Submodule submodule);

  Submodule toEntity(SubmoduleDto submoduleDto);

  List<SubmoduleDto> toDtoList(List<Submodule> submodules);

  List<Submodule> toEntityList(List<SubmoduleDto> submodulesDto);
}