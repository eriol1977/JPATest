/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jpatest.test;

import com.mycompany.jpatest.entity.onetoone.Bullet;
import com.mycompany.jpatest.entity.onetoone.Weapon;
import com.mycompany.jpatest.service.WeaponDAO;
import java.io.IOException;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author f.bertolino
 */
public class TestOneToOne {

    private static WeaponDAO dao;

    @BeforeClass
    public static void setUp() {
        dao = new WeaponDAO();
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
        Weapon rifle = new Weapon("Rifle");
        int weaponId = dao.persistWeapon(rifle);
        
        Bullet shell = new Bullet("Shell");
        shell.setWeapon(rifle);
        int bulletId = dao.persistBullet(shell);
        
        Bullet foundBullet = dao.findBullet(bulletId);
        Assert.assertEquals(weaponId, foundBullet.getWeapon().getId());
    }
}
