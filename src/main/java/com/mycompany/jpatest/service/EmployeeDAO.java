/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jpatest.service;

import com.mycompany.jpatest.entity.Employee;
import java.io.Closeable;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author f.bertolino
 */
public class EmployeeDAO implements Closeable {

    private final EntityManagerFactory emfactory;
    private final EntityManager em;

    public EmployeeDAO() {
        emfactory = Persistence.createEntityManagerFactory("myPU");
        em = emfactory.createEntityManager();
    }

    public int persist(Employee employee) {
        em.getTransaction().begin();
        em.persist(employee);
        em.getTransaction().commit();
        return employee.getEid();
    }

    public Employee find(int id) {
        return em.find(Employee.class, id);
    }

    public void update(Employee employee) {
        em.getTransaction().begin();
        Employee persisted = find(employee.getEid());
        persisted.setEname(employee.getEname());
        persisted.setSalary(employee.getSalary());
        persisted.setDeg(employee.getDeg());
        em.getTransaction().commit();
    }
    
    public void delete(Employee employee) {
        em.getTransaction().begin();
        Employee persisted = find(employee.getEid());
        em.remove(persisted);
        em.getTransaction().commit();
    }
    
    @Override
    public void close() throws IOException {
        em.close();
        emfactory.close();
    }

}
