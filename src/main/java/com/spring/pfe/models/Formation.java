package com.spring.pfe.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "formation")
@JsonIgnoreProperties(value = {"createAt","updateAt"},allowGetters = true)

@EntityListeners(AuditingEntityListener.class)

public class Formation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "titre", nullable = false)
    private  String titre;
    @Column(name = "description", nullable = false)
    private  String description;
    @Column(name = "photo", nullable = false)
    private  String photo;


    private String date_deDebut;


    private String date_defin;


    @ManyToMany
    @JoinTable( name = "users_formation",
            joinColumns = @JoinColumn( name = "id_User" ),
            inverseJoinColumns = @JoinColumn( name = "id_fomation" ) )
    private List<User> users = new ArrayList<>();

   @JsonSerialize(using = CustomListtSerializerTheme.class)
    @OneToMany(targetEntity = Theme.class, cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "formation")
    private List<Theme> themes = new ArrayList<>();



    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDate_deDebut() {
        return date_deDebut;
    }

    public void setDate_deDebut(String date_deDebut) {
        this.date_deDebut = date_deDebut;
    }

    public String getDate_defin() {
        return date_defin;
    }

    public void setDate_defin(String date_defin) {
        this.date_defin = date_defin;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Theme> getThemes() {
        return themes;
    }

    public void setThemes(List<Theme> themes) {
        this.themes = themes;
    }
}
