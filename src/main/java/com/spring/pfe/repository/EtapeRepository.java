package com.spring.pfe.repository;

import com.spring.pfe.models.Etape;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtapeRepository extends JpaRepository<Etape, Long> {
}
