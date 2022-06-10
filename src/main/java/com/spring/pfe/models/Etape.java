package com.spring.pfe.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "etape")

@EntityListeners(AuditingEntityListener.class)
public class Etape implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idEtape;
    @Column(name = "step_titre")
    private  String step_titre;
    @Column(name = "description")
    private  String description;
    @Column(name = "nombre_des_heurs")
    private  String nombre_des_heurs;

    @Column(name = "etapeType")
    private  String etapeType;






    @ManyToOne
    @JsonSerialize(using = CustomSerializer.class)
    private Theme theme;





    public long getIdEtape() {
        return idEtape;
    }

    public void setIdEtape(long idEtape) {
        this.idEtape = idEtape;
    }

    public String getStep_titre() {
        return step_titre;
    }

    public void setStep_titre(String step_titre) {
        this.step_titre = step_titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public String getNombre_des_heurs() {
        return nombre_des_heurs;
    }

    public void setNombre_des_heurs(String nombre_des_heurs) {
        this.nombre_des_heurs = nombre_des_heurs;
    }


    public String getEtapeType() {
        return etapeType;
    }

    public void setEtapeType(String etapeType) {
        this.etapeType = etapeType;
    }
}
