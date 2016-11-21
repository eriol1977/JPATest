/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jpatest.test;

import com.mycompany.jpatest.entity.onetomany.Basket;
import com.mycompany.jpatest.entity.onetomany.Fruit;
import com.mycompany.jpatest.service.BasketDAO;
import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
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
public class TestOneToMany {

    private static BasketDAO dao;

    @BeforeClass
    public static void setUp() {
        dao = new BasketDAO();
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
        Fruit banana = new Fruit("banana");
        Fruit orange = new Fruit("orange");
        Fruit apple = new Fruit("apple");

        int bananaId = dao.persistFruit(banana);
        int orangeId = dao.persistFruit(orange);
        int appleId = dao.persistFruit(apple);

        List<Fruit> fruit = Arrays.asList(new Fruit[]{banana, orange, apple});

        Basket basket = new Basket("basket");
        basket.setFruitlist(fruit);

        int basketId = dao.persistBasket(basket);

        Basket foundBasket = dao.findBasket(basketId);
        Assert.assertEquals(foundBasket.getFruitlist().get(0).getId(), bananaId);
        Assert.assertEquals(foundBasket.getFruitlist().get(1).getId(), orangeId);
        Assert.assertEquals(foundBasket.getFruitlist().get(2).getId(), appleId);

        List<String> tableNames = new ArrayList<>();
        final DatabaseMetaData metadata = dao.getDBMetaData();
        ResultSet rs = metadata.getTables(null, null, "%", new String[]{"TABLE"});
        while (rs.next()) {
            tableNames.add(rs.getString(3).toLowerCase());
        }
        Assert.assertTrue(tableNames.contains("basket_fruit"));
    }
}
