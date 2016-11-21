/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jpatest.test;

import com.mycompany.jpatest.entity.tableperclass.Dragon;
import com.mycompany.jpatest.entity.tableperclass.Troll;
import com.mycompany.jpatest.service.MonsterDAO;
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
public class TestTablePerClassStrategy {

    private static MonsterDAO dao;

    @BeforeClass
    public static void setUp() {
        dao = new MonsterDAO();
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
        Troll troll1 = new Troll(2, "Bungo", 300);
        Troll troll2 = new Troll(4, "Bark", 450);
        Dragon dragon1 = new Dragon(2170, "Eredin", 1200);
        Dragon dragon2 = new Dragon(3400, "Astaroth", 2700);

        dao.persist(troll1);
        dao.persist(troll2);
        dao.persist(dragon1);
        dao.persist(dragon2);

        List<String> tableNames = new ArrayList<>();
        final DatabaseMetaData metadata = dao.getDBMetaData();
        ResultSet rs = metadata.getTables(null, null, "%", new String[]{"TABLE"});
        while (rs.next()) {
            tableNames.add(rs.getString(3).toLowerCase());
        }
        Assert.assertFalse(tableNames.contains("monster"));
        Assert.assertTrue(tableNames.contains("troll"));
        Assert.assertTrue(tableNames.contains("dragon"));
    }
}
