/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jpatest.service;

import com.mycompany.jpatest.entity.onetoone.Bullet;
import com.mycompany.jpatest.entity.onetoone.Weapon;
import javax.persistence.Query;

/**
 *
 * @author f.bertolino
 */
public class WeaponDAO extends AbstractDAO {

    public int persistWeapon(Weapon weapon) {
        em.getTransaction().begin();
        em.persist(weapon);
        em.getTransaction().commit();
        return weapon.getId();
    }

    public int persistBullet(Bullet bullet) {
        em.getTransaction().begin();
        em.persist(bullet);
        em.getTransaction().commit();
        return bullet.getId();
    }

    public Weapon findWeapon(int id) {
        return em.find(Weapon.class, id);
    }
    
    public Bullet findBullet(int id) {
        return em.find(Bullet.class, id);
    }
    
    public void deleteAll() {
        em.getTransaction().begin();
        Query query = em.createNamedQuery("DELETE_ALL_BULLETS");
        query.executeUpdate();
        query = em.createNamedQuery("DELETE_ALL_WEAPONS");
        query.executeUpdate();
        em.getTransaction().commit();
    }
}
