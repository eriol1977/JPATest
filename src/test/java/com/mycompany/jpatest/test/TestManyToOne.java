/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jpatest.test;

import com.mycompany.jpatest.entity.manytoone.Department;
import com.mycompany.jpatest.entity.manytoone.Worker;
import com.mycompany.jpatest.service.DeptAndWorkerDAO;
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
public class TestManyToOne {

    private static DeptAndWorkerDAO dao;

    @BeforeClass
    public static void setUp() {
        dao = new DeptAndWorkerDAO();
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
        Department department = new Department("Engine");
        dao.persistDept(department);

        Worker worker1 = new Worker("Mario Rossi", 2000, "1st Engineer");
        Worker worker2 = new Worker("Luigi Verdi", 3000, "Someone Important");
        worker1.setDepartment(department);
        worker2.setDepartment(department);
        int id1 = dao.persistWorker(worker1);
        int id2 = dao.persistWorker(worker2);

        Worker found1 = dao.findWorker(id1);
        Worker found2 = dao.findWorker(id2);

        Assert.assertEquals(department.getId(), found1.getDepartment().getId());
        Assert.assertEquals(department.getId(), found2.getDepartment().getId());
        Assert.assertEquals("Engine", found1.getDepartment().getName());
    }
}
