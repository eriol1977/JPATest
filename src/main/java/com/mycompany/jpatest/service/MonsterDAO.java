/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jpatest.service;

import com.mycompany.jpatest.entity.tableperclass.Monster;
import javax.persistence.Query;

/**
 *
 * @author f.bertolino
 */
public class MonsterDAO extends AbstractDAO {

    public int persist(Monster monster) {
        em.getTransaction().begin();
        em.persist(monster);
        em.getTransaction().commit();
        return monster.getId();
    }

    public void deleteAll() {
        em.getTransaction().begin();
        final Query query = em.createNamedQuery("DELETE_ALL_MONSTERS");
        query.executeUpdate();
        em.getTransaction().commit();
    }

}
