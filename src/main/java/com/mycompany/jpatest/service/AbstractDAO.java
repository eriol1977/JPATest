/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jpatest.service;

import java.io.Closeable;
import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.hibernate.engine.spi.SessionImplementor;

/**
 *
 * @author f.bertolino
 */
public abstract class AbstractDAO implements Closeable {

    protected final EntityManagerFactory emfactory;
    protected final EntityManager em;

    public AbstractDAO() {
        emfactory = Persistence.createEntityManagerFactory("myPU");
        em = emfactory.createEntityManager();
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

}
