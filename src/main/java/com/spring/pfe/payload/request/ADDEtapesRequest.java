package com.spring.pfe.payload.request;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

public class ADDEtapesRequest {




    private  String step_titre;

    private  String description;
    private  String nombre_des_heurs;

    private Set<String> nameEtapes;

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

    public String getNombre_des_heurs() {
        return nombre_des_heurs;
    }

    public void setNombre_des_heurs(String nombre_des_heurs) {
        this.nombre_des_heurs = nombre_des_heurs;
    }

    public Set<String> getNameEtapes() {
        return nameEtapes;
    }

    public void setNameEtapes(Set<String> nameEtapes) {
        this.nameEtapes = nameEtapes;
    }
}
