/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jpatest.test;

import com.mycompany.jpatest.entity.singletable.NonTeachingStaff;
import com.mycompany.jpatest.entity.singletable.TeachingStaff;
import com.mycompany.jpatest.service.StaffDAO;
import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author f.bertolino
 */
public class TestSingleTableStrategy {

    private static StaffDAO dao;

    @BeforeClass
    public static void setUp() {
        dao = new StaffDAO();
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
    public void testCreateStaff() throws SQLException {
        TeachingStaff ts1 = new TeachingStaff("Gopal", "MSc MEd", "Maths");
        TeachingStaff ts2 = new TeachingStaff("Manisha", "BSc BEd", "English");

        //Non-Teaching Staff entity
        NonTeachingStaff nts1 = new NonTeachingStaff("Satish", "Accounts");
        NonTeachingStaff nts2 = new NonTeachingStaff("Krishna", "Office Admin");

        //storing all entities
        dao.persist(ts1);
        dao.persist(ts2);
        dao.persist(nts1);
        dao.persist(nts2);

        List<String> tableNames = new ArrayList<>();
        final DatabaseMetaData metadata = dao.getDBMetaData();
        ResultSet rs = metadata.getTables(null, null, "%", new String[]{"TABLE"});
        while (rs.next()) {
            tableNames.add(rs.getString(3).toLowerCase());
        }
        Assert.assertTrue(tableNames.contains("staff"));
        Assert.assertFalse(tableNames.contains("teachingstaff"));
        Assert.assertFalse(tableNames.contains("nonteachingstaff"));
    }
}
