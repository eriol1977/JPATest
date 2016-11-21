/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jpatest.entity.tableperclass;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

@NamedQuery(query = "Delete from Monster", name = "DELETE_ALL_MONSTERS")

public abstract class Monster implements Serializable {

    @Id
    @GenericGenerator(name = "table", strategy = "enhanced-table", parameters = {
        @org.hibernate.annotations.Parameter(name = "table_name", value = "sequence_table")
    })
    @GeneratedValue(generator = "table", strategy = GenerationType.TABLE)
    private int id;

    private String name;

    private int vitality;

    public Monster() {
    }

    public Monster(String name, int vitality) {
        this.name = name;
        this.vitality = vitality;
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

    public int getVitality() {
        return vitality;
    }

    public void setVitality(int vitality) {
        this.vitality = vitality;
    }

}
