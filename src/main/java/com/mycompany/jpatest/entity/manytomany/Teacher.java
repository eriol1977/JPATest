/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jpatest.entity.manytomany;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(query = "Delete from Teacher", name = "DELETE_ALL_TEACHERS")
public class Teacher implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int tid;
    private String tname;
    private String subject;

    @ManyToMany(targetEntity = Clazz.class)
    private Set<Clazz> clasSet;

    public Teacher() {
        super();
    }

    public Teacher(String tname, String subject, Set<Clazz> clasSet) {
        super();
        this.tname = tname;
        this.subject = subject;
        this.clasSet = clasSet;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Set<Clazz> getClasSet() {
        return clasSet;
    }

    public void setClasSet(Set<Clazz> clasSet) {
        this.clasSet = clasSet;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Teacher other = (Teacher) obj;
        return this.tid == other.tid;
    }

}
