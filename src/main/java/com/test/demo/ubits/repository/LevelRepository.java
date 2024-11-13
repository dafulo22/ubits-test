package com.test.demo.ubits.repository;

import com.test.demo.ubits.entity.Level;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepository extends JpaRepository<Level, Long> {

  Optional<Level> findByName(String name);
}