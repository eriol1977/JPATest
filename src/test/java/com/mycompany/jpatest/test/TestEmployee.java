/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jpatest.test;

import com.mycompany.jpatest.entity.Employee;
import com.mycompany.jpatest.service.EmployeeDAO;
import java.io.IOException;

import org.junit.AfterClass;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author f.bertolino
 */
public class TestEmployee {

    private static EmployeeDAO dao;

    @BeforeClass
    public static void setUp() {
        dao = new EmployeeDAO();
    }

    @AfterClass
    public static void tearDown() throws IOException {
        dao.close();
    }

    @Test
    public void testEmployeeCreated() {
        Employee employee = new Employee("Francesco Bertolino", 10000, "1st Engineer");
        int id = dao.persist(employee);
        Employee found = dao.find(id);
        assertEquals("Wrong id",employee.getEid(), found.getEid());
        assertEquals("Wrong name",employee.getEname(), found.getEname());
        assertEquals("Wrong salary",employee.getSalary(), found.getSalary(),0);
        assertEquals("Wrong degree",employee.getDeg(), found.getDeg());
    }
    
    @Test
    public void testEmployeeUpdated() {
        Employee employee = new Employee("Francesco Bertolino", 10000, "1st Engineer");
        int id = dao.persist(employee);
        Employee found = dao.find(id);
        assertEquals("Wrong salary",10000, found.getSalary(),0);
        employee.setSalary(12000);
        dao.update(employee);
        found = dao.find(id);
        assertEquals("Wrong salary",12000, found.getSalary(),0);
    }
    
    @Test
    public void testEmployeeDeleted() {
        Employee employee = new Employee("Francesco Bertolino", 10000, "1st Engineer");
        int id = dao.persist(employee);
        Employee found = dao.find(id);
        assertNotNull(found);
        dao.delete(employee);
        found = dao.find(id);
        assertNull(found);
    }
}
