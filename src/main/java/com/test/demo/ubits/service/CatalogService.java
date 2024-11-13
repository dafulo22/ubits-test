package com.test.demo.ubits.service;


import com.test.demo.ubits.dto.CatalogDto;
import com.test.demo.ubits.entity.Catalog;
import com.test.demo.ubits.entity.Category;
import com.test.demo.ubits.entity.Level;
import com.test.demo.ubits.entity.Partner;
import com.test.demo.ubits.mapper.CatalogMapper;
import com.test.demo.ubits.repository.CatalogRepository;
import com.test.demo.ubits.repository.CategoryRepository;
import com.test.demo.ubits.repository.LevelRepository;
import com.test.demo.ubits.repository.PartnerRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CatalogService {

  private final CatalogRepository catalogRepository;
  private final LevelRepository levelRepository;
  private final PartnerRepository partnerRepository;
  private final CategoryRepository categoryRepository;
  private final CatalogMapper catalogMapper;

  @Autowired
  public CatalogService(CatalogRepository catalogRepository, LevelRepository levelRepository,
      PartnerRepository partnerRepository, CategoryRepository categoryRepository,
      CatalogMapper catalogMapper) {
    this.catalogRepository = catalogRepository;
    this.levelRepository = levelRepository;
    this.partnerRepository = partnerRepository;
    this.categoryRepository = categoryRepository;
    this.catalogMapper = catalogMapper;
  }

  public List<CatalogDto> findAll() {
    List<Catalog> catalogs = catalogRepository.findAll();
    return catalogMapper.toDtoList(catalogs);
  }

  public CatalogDto findById(Long id) {
    Catalog catalog = catalogRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Catalog not found"));
    return catalogMapper.toDto(catalog);
  }

  public CatalogDto createCatalog(CatalogDto catalogDto) {
    Catalog catalog = catalogMapper.toEntity(catalogDto);

    // Associate Relations
    associateForeignKeys(catalogDto, catalog);

    catalog = catalogRepository.save(catalog);
    return catalogMapper.toDto(catalog);
  }

  public CatalogDto updateCatalog(Long id, CatalogDto catalogDto) {
    if (!catalogRepository.existsById(id)) {
      return null;
    }

    Catalog catalog = catalogMapper.toEntity(catalogDto);

    // Associate Relations
    associateForeignKeys(catalogDto, catalog);

    catalog = catalogRepository.save(catalog);
    return catalogMapper.toDto(catalog);
  }

  public boolean delete(Long id) {
    if (catalogRepository.existsById(id)) {
      catalogRepository.deleteById(id);
      return true;
    }
    return false;
  }

  private void associateForeignKeys(CatalogDto catalogDto, Catalog catalog) {
    // Associate Level
    if (catalogDto.getLevelName() != null) {
      Level level = getLevelByName(catalogDto.getLevelName());
      catalog.setLevel(level);
    }

    // Associate Partner
    if (catalogDto.getPartnerName() != null) {
      Partner partner = getPartnerByName(catalogDto.getPartnerName());
      catalog.setPartner(partner);
    }

    // Associate Category
    if (catalogDto.getCategoryName() != null) {
      Category category = getCategoryByName(catalogDto.getCategoryName());
      catalog.setCategory(category);
    }
  }

  private Level getLevelByName(String name) {
    return levelRepository.findByName(name)
        .orElseThrow(() -> new EntityNotFoundException("Level not found"));
  }

  private Partner getPartnerByName(String name) {
    return partnerRepository.findByName(name)
        .orElseThrow(() -> new EntityNotFoundException("Partner not found"));
  }

  private Category getCategoryByName(String name) {
    return categoryRepository.findByName(name)
        .orElseThrow(() -> new EntityNotFoundException("Category not found"));
  }
}