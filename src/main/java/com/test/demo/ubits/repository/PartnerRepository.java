package com.test.demo.ubits.repository;

import com.test.demo.ubits.entity.Partner;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {

  Optional<Partner> findByName(String name);
}