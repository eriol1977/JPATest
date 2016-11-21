/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jpatest.service;

import com.mycompany.jpatest.entity.singletable.Staff;
import java.io.Closeable;
import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.hibernate.engine.spi.SessionImplementor;

/**
 *
 * @author f.bertolino
 */
public class StaffDAO implements Closeable {

    private final EntityManagerFactory emfactory;
    private final EntityManager em;

    public StaffDAO() {
        emfactory = Persistence.createEntityManagerFactory("myPU");
        em = emfactory.createEntityManager();
    }

    public int persist(Staff staff) {
        em.getTransaction().begin();
        em.persist(staff);
        em.getTransaction().commit();
        return staff.getSid();
    }

    public DatabaseMetaData getDBMetaData() throws SQLException {
        SessionImplementor sessionImp = (SessionImplementor) em.getDelegate();
        return sessionImp.connection().getMetaData();
    }

    @Override
    public void close() throws IOException {
        em.close();
        emfactory.close();
    }

    public void deleteAll() {
        em.getTransaction().begin();
        final Query query = em.createNamedQuery("DELETE_ALL_STAFF");
        query.executeUpdate();
        em.getTransaction().commit();
    }

}
