/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jpatest.service;

import com.mycompany.jpatest.entity.singletable.Staff;
import javax.persistence.Query;

/**
 *
 * @author f.bertolino
 */
public class StaffDAO extends AbstractDAO {

    public int persist(Staff staff) {
        em.getTransaction().begin();
        em.persist(staff);
        em.getTransaction().commit();
        return staff.getSid();
    }

    public void deleteAll() {
        em.getTransaction().begin();
        final Query query = em.createNamedQuery("DELETE_ALL_STAFF");
        query.executeUpdate();
        em.getTransaction().commit();
    }

}
