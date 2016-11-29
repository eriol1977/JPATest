/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jpatest.filestorage;

import java.io.Closeable;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class FileStorageSessionFacade extends AbstractSessionFacade<FileStorageEntity> implements IFileStorageSessionFacadeLocal, Closeable {

    private EntityManagerFactory factory;

    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        if (em == null) {
            factory = Persistence.createEntityManagerFactory("myPU");
            em = factory.createEntityManager();
        }
        return em;
    }

    public FileStorageSessionFacade() {
        super(FileStorageEntity.class);
    }

    public FileStorageEntity getEntityByName(String fileName) {
        Query query = getEntityManager().createNamedQuery("FileStorage.findByFileName");
        query.setParameter("fileName", fileName);
        return (FileStorageEntity) query.getSingleResult();
    }

    @Override
    public void close() throws IOException {
        em.close();
        factory.close();
    }

}
