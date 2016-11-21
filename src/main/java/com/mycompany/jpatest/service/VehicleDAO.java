/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jpatest.service;

import com.mycompany.jpatest.entity.joinedtable.Vehicle;
import javax.persistence.Query;

/**
 *
 * @author f.bertolino
 */
public class VehicleDAO extends AbstractDAO {

    public int persist(Vehicle vehicle) {
        em.getTransaction().begin();
        em.persist(vehicle);
        em.getTransaction().commit();
        return vehicle.getId();
    }

    public void deleteAll() {
        em.getTransaction().begin();
        final Query query = em.createNamedQuery("DELETE_ALL_VEHICLE");
        query.executeUpdate();
        em.getTransaction().commit();
    }

}
