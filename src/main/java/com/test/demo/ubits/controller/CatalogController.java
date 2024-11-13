package com.test.demo.ubits.controller;

import com.test.demo.ubits.dto.CatalogDto;
import com.test.demo.ubits.service.CatalogService;
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
@RequestMapping("/api/catalog")
public class CatalogController {

  private final CatalogService catalogService;

  @Autowired
  public CatalogController(CatalogService catalogService) {
    this.catalogService = catalogService;
  }

  @GetMapping
  public ResponseEntity<List<CatalogDto>> getAllCatalogs() {
    List<CatalogDto> catalogs = catalogService.findAll();
    return ResponseEntity.ok(catalogs);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CatalogDto> getCatalogById(@PathVariable Long id) {
    CatalogDto catalog = catalogService.findById(id);
    if (catalog == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(catalog);
  }

  @PostMapping
  public ResponseEntity<CatalogDto> createCatalog(@RequestBody CatalogDto catalogDto) {
    CatalogDto createdCatalog = catalogService.createCatalog(catalogDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdCatalog);
  }

  @PutMapping("/{id}")
  public ResponseEntity<CatalogDto> updateCatalog(@PathVariable Long id,
      @RequestBody CatalogDto catalogDto) {
    CatalogDto updatedCatalog = catalogService.updateCatalog(id, catalogDto);
    if (updatedCatalog == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(updatedCatalog);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCatalog(@PathVariable Long id) {
    boolean deleted = catalogService.delete(id);
    if (!deleted) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.noContent().build();
  }
}