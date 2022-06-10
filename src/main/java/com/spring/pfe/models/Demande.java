package com.spring.pfe.models;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "demande")

@EntityListeners(AuditingEntityListener.class)
public class Demande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "active")
    private  Boolean active;

    @Column(name = "date")
    private  String date;

    @ManyToOne
    @JsonSerialize(using = CustomSerializerUser.class)

    private User users;

    @ManyToOne
    @JsonSerialize(using = CustomSerializerFormation.class)

    private Formation formationn;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }



    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }



    public Formation getFormationn() {
        return formationn;
    }

    public void setFormationn(Formation formationn) {
        this.formationn = formationn;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
