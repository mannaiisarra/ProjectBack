package com.spring.pfe.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "demande")

@EntityListeners(AuditingEntityListener.class)
public class Demande implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "active")
    private  String active;



    @ManyToOne
    //@JsonSerialize(using = CustomSerializerFormation.class)

    private Formation formationn;

    @ManyToOne
    private User users;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }





    public Formation getFormationn() {
        return formationn;
    }

    public void setFormationn(Formation formationn) {
        this.formationn = formationn;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }
}
