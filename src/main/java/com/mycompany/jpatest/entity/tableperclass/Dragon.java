/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jpatest.entity.tableperclass;

import javax.persistence.Entity;

@Entity
public class Dragon extends Monster {

    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Dragon() {
    }

    public Dragon(int age, String name, int vitality) {
        super(name, vitality);
        this.age = age;
    }

}
