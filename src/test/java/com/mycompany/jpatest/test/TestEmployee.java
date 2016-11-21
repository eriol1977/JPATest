/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jpatest.test;

import com.mycompany.jpatest.entity.Employee;
import com.mycompany.jpatest.service.EmployeeDAO;
import java.io.IOException;
import java.util.List;
import org.junit.After;

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

    @After
    public void deleteAll() {
        dao.deleteAll();
    }

    @Test
    public void testEmployeeCreated() {
        Employee employee = new Employee("Francesco Bertolino", 10000, "1st Engineer");
        int id = dao.persist(employee);
        Employee found = dao.find(id);
        assertEquals("Wrong id", employee.getEid(), found.getEid());
        assertEquals("Wrong name", employee.getEname(), found.getEname());
        assertEquals("Wrong salary", employee.getSalary(), found.getSalary(), 0);
        assertEquals("Wrong degree", employee.getDeg(), found.getDeg());
    }

    @Test
    public void testEmployeeUpdated() {
        Employee employee = new Employee("Francesco Bertolino", 10000, "1st Engineer");
        int id = dao.persist(employee);
        Employee found = dao.find(id);
        assertEquals("Wrong salary", 10000, found.getSalary(), 0);
        employee.setSalary(12000);
        dao.update(employee);
        found = dao.find(id);
        assertEquals("Wrong salary", 12000, found.getSalary(), 0);
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

    @Test
    public void testFindEmployeeByName() {
        String name = "Francesco Bertolino";
        Employee employee = new Employee(name, 10000, "1st Engineer");
        dao.persist(employee);
        Employee found = dao.findByName(name);
        assertEquals(employee.getEid(), found.getEid());
    }

    @Test
    public void testFindAllEmployees() {
        Employee employee1 = new Employee("Mario Rossi", 10000, "1st Engineer");
        Employee employee2 = new Employee("Giacomo Verdi", 8000, "Staff Officer");
        Employee employee3 = new Employee("Paolo Gialli", 15500, "Captain");
        dao.persist(employee1);
        dao.persist(employee2);
        dao.persist(employee3);
        List<Employee> employees = dao.findAllEmployees();
        assertEquals(3, employees.size());
        assertEquals(employee1.getEid(), employees.get(0).getEid());
        assertEquals(employee2.getEid(), employees.get(1).getEid());
        assertEquals(employee3.getEid(), employees.get(2).getEid());
    }

    @Test
    public void testFindEmployeesInSalaryRange() {
        Employee employee1 = new Employee("Mario Rossi", 10000, "1st Engineer");
        Employee employee2 = new Employee("Giacomo Verdi", 8000, "Staff Officer");
        Employee employee3 = new Employee("Paolo Gialli", 15500, "Captain");
        Employee employee4 = new Employee("Alessandro Bianchi", 13000, "Captain");
        dao.persist(employee1);
        dao.persist(employee2);
        dao.persist(employee3);
        dao.persist(employee4);
        List<Employee> employees = dao.findInSalaryRange(10000d, 14000d);
        assertEquals(2, employees.size());
        assertEquals("Mario Rossi", employees.get(0).getEname());
        assertEquals("Alessandro Bianchi", employees.get(1).getEname());
    }

    @Test
    public void testFindEmployeesByNameStart() {
        Employee employee1 = new Employee("Mario Rossi", 10000, "1st Engineer");
        Employee employee2 = new Employee("Giacomo Verdi", 8000, "Staff Officer");
        Employee employee3 = new Employee("Marco Gialli", 15500, "Captain");
        Employee employee4 = new Employee("Alessandro Bianchi", 13000, "Captain");
        dao.persist(employee1);
        dao.persist(employee2);
        dao.persist(employee3);
        dao.persist(employee4);
        List<Employee> employees = dao.findEmployeesByNameStart("Mar");
        assertEquals(2, employees.size());
        assertEquals("Mario Rossi", employees.get(0).getEname());
        assertEquals("Marco Gialli", employees.get(1).getEname());
    }

    @Test
    public void testFindEmployeesByDescendingSalary() {
        Employee employee1 = new Employee("Mario Rossi", 10000, "1st Engineer");
        Employee employee2 = new Employee("Giacomo Verdi", 8000, "Staff Officer");
        Employee employee3 = new Employee("Marco Gialli", 15500, "Captain");
        Employee employee4 = new Employee("Alessandro Bianchi", 13000, "Captain");
        dao.persist(employee1);
        dao.persist(employee2);
        dao.persist(employee3);
        dao.persist(employee4);
        List<Employee> employees = dao.findEmployeesByDescendingSalary();
        assertEquals(4, employees.size());
        assertEquals("Marco Gialli", employees.get(0).getEname());
        assertEquals("Alessandro Bianchi", employees.get(1).getEname());
        assertEquals("Mario Rossi", employees.get(2).getEname());
        assertEquals("Giacomo Verdi", employees.get(3).getEname());
    }

    @Test
    public void testFindMaxSalary() {
        Employee employee1 = new Employee("Mario Rossi", 10000, "1st Engineer");
        Employee employee2 = new Employee("Giacomo Verdi", 8000, "Staff Officer");
        Employee employee3 = new Employee("Marco Gialli", 15500, "Captain");
        Employee employee4 = new Employee("Alessandro Bianchi", 13000, "Captain");
        dao.persist(employee1);
        dao.persist(employee2);
        dao.persist(employee3);
        dao.persist(employee4);
        Double maxSalary = dao.findMaxSalary();
        assertEquals(15500, maxSalary, 0);
    }
}
