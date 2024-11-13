package com.application.demo.ubits.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.test.demo.ubits.dto.CatalogDto;
import com.test.demo.ubits.entity.Catalog;
import com.test.demo.ubits.entity.Category;
import com.test.demo.ubits.entity.Level;
import com.test.demo.ubits.entity.Partner;
import com.test.demo.ubits.mapper.CatalogMapper;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class CatalogMapperTest {

  private final CatalogMapper catalogMapper = Mappers.getMapper(CatalogMapper.class);

  @Test
  void toDto_ShouldConvertCatalogToCatalogDto() {
    // Positive Case
    Catalog catalog = Catalog.builder().build();
    catalog.setPartner(Partner.builder().name("Partner1").build());
    catalog.setCategory(Category.builder().name("Category1").build());
    catalog.setLevel(Level.builder().name("Level1").build());

    CatalogDto catalogDto = catalogMapper.toDto(catalog);

    assertEquals("Partner1", catalogDto.getPartnerName());
    assertEquals("Category1", catalogDto.getCategoryName());
    assertEquals("Level1", catalogDto.getLevelName());
  }

  @Test
  void toDto_ShouldReturnNull_WhenCatalogIsNull() {
    // Negative Case
    CatalogDto catalogDto = catalogMapper.toDto(null);
    assertNull(catalogDto);
  }

  @Test
  void toEntity_ShouldConvertCatalogDtoToCatalog() {
    // Positive Case
    CatalogDto catalogDto = CatalogDto.builder()
        .partnerName("Partner1")
        .categoryName("Category1")
        .levelName("Level1")
        .build();

    Catalog catalog = catalogMapper.toEntity(catalogDto);

    assertNull(catalog.getPartner());
    assertNull(catalog.getCategory());
    assertNull(catalog.getLevel());
  }

  @Test
  void toEntity_ShouldReturnNull_WhenCatalogDtoIsNull() {
    // Negative Case
    Catalog catalog = catalogMapper.toEntity(null);
    assertNull(catalog);
  }

  @Test
  void toDtoList_ShouldConvertListOfCatalogToListOfCatalogDto() {
    // Positive Case
    Catalog catalog1 = Catalog.builder().build();
    catalog1.setPartner(Partner.builder().name("Partner1").build());
    catalog1.setCategory(Category.builder().name("Category1").build());
    catalog1.setLevel(Level.builder().name("Level1").build());

    Catalog catalog2 = Catalog.builder().build();
    catalog2.setPartner(Partner.builder().name("Partner2").build());
    catalog2.setCategory(Category.builder().name("Category2").build());
    catalog2.setLevel(Level.builder().name("Level2").build());

    List<CatalogDto> catalogDtoList = catalogMapper.toDtoList(Arrays.asList(catalog1, catalog2));

    assertEquals(2, catalogDtoList.size());
    assertEquals("Partner1", catalogDtoList.get(0).getPartnerName());
    assertEquals("Partner2", catalogDtoList.get(1).getPartnerName());
  }

  @Test
  void toDtoList_ShouldReturnEmptyList_WhenInputIsEmpty() {
    // Negative Case
    List<CatalogDto> catalogDtoList = catalogMapper.toDtoList(Collections.emptyList());
    assertTrue(catalogDtoList.isEmpty());
  }

  @Test
  void toEntityList_ShouldConvertListOfCatalogDtoToListOfCatalog() {
    // Positive Case
    CatalogDto catalogDto1 = CatalogDto.builder().build();
    catalogDto1.setPartnerName("Partner1");
    catalogDto1.setCategoryName("Category1");
    catalogDto1.setLevelName("Level1");

    CatalogDto catalogDto2 = CatalogDto.builder().build();
    catalogDto2.setPartnerName("Partner2");
    catalogDto2.setCategoryName("Category2");
    catalogDto2.setLevelName("Level2");

    List<Catalog> catalogList = catalogMapper.toEntityList(Arrays.asList(catalogDto1, catalogDto2));

    assertEquals(2, catalogList.size());
    assertNull(catalogList.get(0).getPartner());
    assertNull(catalogList.get(1).getPartner());
  }

  @Test
  void toEntityList_ShouldReturnEmptyList_WhenInputIsEmpty() {
    // Negative Case
    List<Catalog> catalogList = catalogMapper.toEntityList(Collections.emptyList());
    assertTrue(catalogList.isEmpty());
  }
}