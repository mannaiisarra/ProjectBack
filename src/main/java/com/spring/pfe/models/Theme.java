package com.spring.pfe.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "theme")
@EntityListeners(AuditingEntityListener.class)
public class Theme implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "theme_titre", nullable = false)
    private  String theme_titre;

    @ManyToOne
    @JsonSerialize(using = CustomSerializerFormation.class)
    private Formation formation;

    @JsonSerialize(using = CustomListSerializer.class)
    @OneToMany(targetEntity = Etape.class, cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "theme")
    private List<Etape> etapes = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTheme_titre() {
        return theme_titre;
    }

    public void setTheme_titre(String theme_titre) {
        this.theme_titre = theme_titre;
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    public List<Etape> getEtapes() {
        return etapes;
    }

    public void setEtapes(List<Etape> etapes) {
        this.etapes = etapes;
    }


}
