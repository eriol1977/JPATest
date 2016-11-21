/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jpatest.service;

import com.mycompany.jpatest.entity.onetomany.Basket;
import com.mycompany.jpatest.entity.onetomany.Fruit;
import javax.persistence.Query;

/**
 *
 * @author f.bertolino
 */
public class BasketDAO extends AbstractDAO {
    
    public int persistBasket(Basket basket) {
        em.getTransaction().begin();
        em.persist(basket);
        em.getTransaction().commit();
        return basket.getId();
    }
    
    public int persistFruit(Fruit fruit) {
        em.getTransaction().begin();
        em.persist(fruit);
        em.getTransaction().commit();
        return fruit.getId();
    }
    
    public Basket findBasket(int id) {
        return em.find(Basket.class, id);
    }
    
    public void deleteAll() {
        em.getTransaction().begin();
        Query query = em.createNamedQuery("DELETE_ALL_BASKETS"); // deletes baskets and basket-fruit associations
        query.executeUpdate();
        query = em.createNamedQuery("DELETE_ALL_FRUIT"); // deletes fruit
        query.executeUpdate();
        em.getTransaction().commit();
    }
}
