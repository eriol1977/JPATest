/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jpatest.entity.tableperclass;

import javax.persistence.Entity;

@Entity
public class Troll extends Monster {

    private int neurons;

    public int getNeurons() {
        return neurons;
    }

    public void setNeurons(int neurons) {
        this.neurons = neurons;
    }

    public Troll() {
    }

    public Troll(int neurons, String name, int vitality) {
        super(name, vitality);
        this.neurons = neurons;
    }

}
