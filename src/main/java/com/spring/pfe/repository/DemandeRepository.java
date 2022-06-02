package com.spring.pfe.repository;

import com.spring.pfe.models.Demande;
import com.spring.pfe.models.Etape;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandeRepository extends JpaRepository<Demande, Long> {
}
