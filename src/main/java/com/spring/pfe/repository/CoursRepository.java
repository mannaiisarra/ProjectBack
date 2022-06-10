package com.spring.pfe.repository;

import com.spring.pfe.models.Cours;
import com.spring.pfe.models.Demande;
import com.spring.pfe.models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursRepository extends JpaRepository<Cours, Long> {
}
