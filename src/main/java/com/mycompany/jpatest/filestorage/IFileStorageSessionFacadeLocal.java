/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jpatest.filestorage;

import java.util.List;

public interface IFileStorageSessionFacadeLocal {

    public void create(FileStorageEntity fileStorageEntity);

    public void edit(FileStorageEntity fileStorageEntity);

    public void remove(FileStorageEntity fileStorageEntity);

    public FileStorageEntity find(Object id);

    public List<FileStorageEntity> findAll();

    public List<FileStorageEntity> findRange(int[] range);

    public int count();
}
