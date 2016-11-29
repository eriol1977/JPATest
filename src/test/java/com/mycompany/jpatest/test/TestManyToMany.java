/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jpatest.test;

import com.mycompany.jpatest.entity.manytomany.Clazz;
import com.mycompany.jpatest.entity.manytomany.Teacher;
import com.mycompany.jpatest.service.ClazzDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author f.bertolino
 */
public class TestManyToMany {

    private static ClazzDAO dao;

    @BeforeClass
    public static void setUp() {
        dao = new ClazzDAO();
    }

    @AfterClass
    public static void tearDown() throws IOException {
        dao.close();
    }

    @After
    public void deleteAll() {
        dao.deleteAll();
    }

    @Test
    public void testCreateTables() throws SQLException {
        //Create Clazz Entity
        Clazz clas1 = new Clazz("1st");
        Clazz clas2 = new Clazz("2nd");
        Clazz clas3 = new Clazz("3rd");

        //Store Clazz
        dao.persistClazz(clas1);
        dao.persistClazz(clas2);
        dao.persistClazz(clas3);

        //Create Clazz Set1
        Set<Clazz> classSet1 = new HashSet();
        classSet1.add(clas1);
        classSet1.add(clas2);

        //Create Clazz Set2
        Set<Clazz> classSet2 = new HashSet();
        classSet2.add(clas3);
        classSet2.add(clas1);

        //Create Clazz Set3
        Set<Clazz> classSet3 = new HashSet();
        classSet3.add(clas2);
        classSet3.add(clas3);

        //Create Teacher Entity
        Teacher teacher1 = new Teacher("Satish", "Java", classSet1);
        Teacher teacher2 = new Teacher("Krishna", "Adv Java", classSet2);
        Teacher teacher3 = new Teacher("Masthanvali", "DB2", classSet3);

        //Store Teacher
        int tid1 = dao.persistTeacher(teacher1);
        int tid2 =  dao.persistTeacher(teacher2);
        int tid3 = dao.persistTeacher(teacher3);
        
        //Finds classes from teachers
        // Obs: this is a unidirectional many to many, I cannot find the teachers from the classes!
        // Many to many just means that the same class may be assigned to more than a teacher at the same time,
        // unlike one to many, in which, for example, the same banana cannot stay in more than a basket at the same time
        Teacher tfound1 = dao.findTeacher(tid1);
        Teacher tfound2 = dao.findTeacher(tid2);
        Teacher tfound3 = dao.findTeacher(tid3);
        
        Set<Clazz> clasSet1 = tfound1.getClasSet();
        Assert.assertTrue(clasSet1.contains(clas1));
        Assert.assertTrue(clasSet1.contains(clas2));
        Assert.assertFalse(clasSet1.contains(clas3));
        
        Set<Clazz> clasSet2 = tfound2.getClasSet();
        Assert.assertTrue(clasSet2.contains(clas1));
        Assert.assertFalse(clasSet2.contains(clas2));
        Assert.assertTrue(clasSet2.contains(clas3));
        
        Set<Clazz> clasSet3 = tfound3.getClasSet();
        Assert.assertFalse(clasSet3.contains(clas1));
        Assert.assertTrue(clasSet3.contains(clas2));
        Assert.assertTrue(clasSet3.contains(clas3));
    }
}
