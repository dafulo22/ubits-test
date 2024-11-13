package com.test.demo.ubits.mapper;

import com.test.demo.ubits.dto.CatalogDto;
import com.test.demo.ubits.entity.Catalog;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CatalogMapper {

  CatalogMapper INSTANCE = Mappers.getMapper(CatalogMapper.class);

  @Mapping(source = "partner.name", target = "partnerName")
  @Mapping(source = "category.name", target = "categoryName")
  @Mapping(source = "level.name", target = "levelName")
  CatalogDto toDto(Catalog catalog);

  @Mapping(target = "level", ignore = true)    // We ignore to assign in the service
  @Mapping(target = "partner", ignore = true)  // We ignore to assign in the service
  @Mapping(target = "category", ignore = true)
    // We ignore to assign in the service
  Catalog toEntity(CatalogDto catalogDto);

  List<CatalogDto> toDtoList(List<Catalog> catalogs);

  List<Catalog> toEntityList(List<CatalogDto> catalogsDto);
}