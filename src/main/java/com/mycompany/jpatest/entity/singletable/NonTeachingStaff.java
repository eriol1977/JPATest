/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jpatest.entity.singletable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "NS")

public class NonTeachingStaff extends Staff {

    private String areaexpertise;

    public NonTeachingStaff(String sname, String areaexpertise) {
        super(sname);
        this.areaexpertise = areaexpertise;
    }

    public NonTeachingStaff() {
        super();
    }

    public String getAreaexpertise() {
        return areaexpertise;
    }

    public void setAreaexpertise(String areaexpertise) {
        this.areaexpertise = areaexpertise;
    }
}
