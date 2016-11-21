/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jpatest.service;

import com.mycompany.jpatest.entity.manytoone.Department;
import com.mycompany.jpatest.entity.manytoone.Worker;
import javax.persistence.Query;

/**
 *
 * @author f.bertolino
 */
public class DeptAndWorkerDAO extends AbstractDAO {

    public int persistDept(Department dept) {
        em.getTransaction().begin();
        em.persist(dept);
        em.getTransaction().commit();
        return dept.getId();
    }

    public int persistWorker(Worker worker) {
        em.getTransaction().begin();
        em.persist(worker);
        em.getTransaction().commit();
        return worker.getEid();
    }

    public Worker findWorker(int id) {
        return em.find(Worker.class, id);
    }
    
    public void deleteAll() {
        em.getTransaction().begin();
        Query query = em.createNamedQuery("DELETE_ALL_WORKERS");
        query.executeUpdate();
        query = em.createNamedQuery("DELETE_ALL_DEPARTMENTS");
        query.executeUpdate();
        em.getTransaction().commit();
    }
}
