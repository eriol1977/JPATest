/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jpatest.test;

import com.mycompany.jpatest.entity.joinedtable.Bicycle;
import com.mycompany.jpatest.entity.joinedtable.Car;
import com.mycompany.jpatest.service.VehicleDAO;
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
public class TestJoinedTableStrategy {

    private static VehicleDAO dao;

    @BeforeClass
    public static void setUp() {
        dao = new VehicleDAO();
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
        Car car1 = new Car("1234", "Ferrari");
        Car car2 = new Car("9876", "Peugeot");
        Bicycle bicycle1 = new Bicycle(15, "XYZ");
        Bicycle bicycle2 = new Bicycle(30, "WWA");
        
        dao.persist(car1);
        dao.persist(car2);
        dao.persist(bicycle1);
        dao.persist(bicycle2);

        List<String> tableNames = new ArrayList<>();
        final DatabaseMetaData metadata = dao.getDBMetaData();
        ResultSet rs = metadata.getTables(null, null, "%", new String[]{"TABLE"});
        while (rs.next()) {
            tableNames.add(rs.getString(3).toLowerCase());
        }
        Assert.assertTrue(tableNames.contains("vehicle"));
        Assert.assertTrue(tableNames.contains("car"));
        Assert.assertTrue(tableNames.contains("bicycle"));
    }
}
