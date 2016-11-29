/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jpatest.filestorage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author f.bertolino
 */
public class FileStorageService {

    public void persistFile(String path) throws IOException {
        Path filePath = Paths.get(path);
        String fileName = filePath.getFileName().toString();
        byte[] fileContent = Files.readAllBytes(filePath);
        FileStorageEntity fse = new FileStorageEntity(fileName, fileContent);
        try (FileStorageSessionFacade facade = new FileStorageSessionFacade()) {
            facade.create(fse);
        }
    }

    public void extractFile(String fileName, String destination) throws IOException {
        try (FileStorageSessionFacade facade = new FileStorageSessionFacade()) {
            FileStorageEntity fse = facade.getEntityByName(fileName);
            Files.write(Paths.get(destination + "/" + fileName), fse.getContent());
        }
    }
    
    public void deleteFile(String fileName) throws IOException {
        try (FileStorageSessionFacade facade = new FileStorageSessionFacade()) {
            FileStorageEntity fse = facade.getEntityByName(fileName);
            facade.remove(fse);
        }
    }
}
