/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jpatest.entity.onetomany;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity

@NamedQuery(query = "Delete from Basket", name = "DELETE_ALL_BASKETS")

public class Basket implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int id;
    private String name;

    @OneToMany(targetEntity = Fruit.class)
    private List<Fruit> fruitlist;

    public Basket() {
    }

    public Basket(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Fruit> getFruitlist() {
        return fruitlist;
    }

    public void setFruitlist(List<Fruit> fruitlist) {
        this.fruitlist = fruitlist;
    }
}
