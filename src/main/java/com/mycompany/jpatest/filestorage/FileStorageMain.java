/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jpatest.filestorage;

import java.io.IOException;

/**
 *
 * @author f.bertolino
 */
public class FileStorageMain {

    public static void main(String[] args) throws IOException {
        final FileStorageService fss = new FileStorageService();
        final String fileName = "orario.xlsx";
        fss.persistFile(fileName);
        fss.extractFile(fileName, "C:/temp");
        fss.deleteFile(fileName);
    }
}
