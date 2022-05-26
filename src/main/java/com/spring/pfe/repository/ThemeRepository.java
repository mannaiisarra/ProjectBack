package com.spring.pfe.repository;

import com.spring.pfe.models.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Long> {
    @Query("Select c from Theme c where c.formation.id = :id ")
    List<Theme> getAllThemeByFormation(@Param("id") Long id);
}
