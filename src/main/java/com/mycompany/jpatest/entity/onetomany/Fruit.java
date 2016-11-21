/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jpatest.entity.onetomany;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity

@NamedQuery(query = "Delete from Fruit", name = "DELETE_ALL_FRUIT")

public class Fruit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int id;
    private String ename;

    public Fruit(String ename) {
        super();
        this.ename = ename;
    }

    public Fruit() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

}
