/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jpatest.service;

import com.mycompany.jpatest.entity.manytomany.Clazz;
import com.mycompany.jpatest.entity.manytomany.Teacher;
import javax.persistence.Query;

/**
 *
 * @author f.bertolino
 */
public class ClazzDAO extends AbstractDAO {

    public int persistClazz(Clazz clazz) {
        em.getTransaction().begin();
        em.persist(clazz);
        em.getTransaction().commit();
        return clazz.getCid();
    }

    public int persistTeacher(Teacher teacher) {
        em.getTransaction().begin();
        em.persist(teacher);
        em.getTransaction().commit();
        return teacher.getTid();
    }

    public Clazz findClazz(int id) {
        return em.find(Clazz.class, id);
    }
    
    public Teacher findTeacher(int id) {
        return em.find(Teacher.class, id);
    }
    
    public void deleteAll() {
        em.getTransaction().begin();
        Query query = em.createNamedQuery("DELETE_ALL_TEACHERS");
        query.executeUpdate();
        query = em.createNamedQuery("DELETE_ALL_CLASSES");
        query.executeUpdate();
        em.getTransaction().commit();
    }
}
