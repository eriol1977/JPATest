/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jpatest.service;

import com.mycompany.jpatest.entity.Employee;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author f.bertolino
 */
public class EmployeeDAO extends AbstractDAO {

    public int persist(Employee employee) {
        em.getTransaction().begin();
        em.persist(employee);
        em.getTransaction().commit();
        return employee.getEid();
    }

    public Employee find(int id) {
        return em.find(Employee.class, id);
    }

    public void update(Employee employee) {
        em.getTransaction().begin();
        Employee persisted = find(employee.getEid());
        persisted.setEname(employee.getEname());
        persisted.setSalary(employee.getSalary());
        persisted.setDeg(employee.getDeg());
        em.getTransaction().commit();
    }

    public void delete(Employee employee) {
        em.getTransaction().begin();
        Employee persisted = find(employee.getEid());
        em.remove(persisted);
        em.getTransaction().commit();
    }

    public void deleteAll() {
        em.getTransaction().begin();
        Query query = em.createNamedQuery("DELETE_ALL_EMPLOYEES");
        query.executeUpdate();
        em.getTransaction().commit();
    }

    public Employee findByName(String name) {
        Query query = em.createNamedQuery("FIND_EMPLOYEE_BY_NAME");
        query.setParameter("name", name);
        return (Employee) query.getSingleResult();
    }

    public List<Employee> findAllEmployees() {
        Query query = em.createNamedQuery("FIND_ALL_EMPLOYEES");
        return query.getResultList();
    }

    public List<Employee> findInSalaryRange(Double lowest, Double highest) {
        Query query = em.createNamedQuery("FIND_EMPLOYEES_IN_SALARY_RANGE");
        query.setParameter("lowest", lowest);
        query.setParameter("highest", highest);
        return query.getResultList();
    }

    public List<Employee> findEmployeesByNameStart(String nameStart) {
        Query query = em.createNamedQuery("FIND_EMPLOYEES_BY_NAME_START");
        query.setParameter("nameStart", nameStart + '%');
        return query.getResultList();
    }

    public List<Employee> findEmployeesByDescendingSalary() {
        Query query = em.createNamedQuery("FIND_EMPLOYEES_BY_DESC_SALARY");
        return query.getResultList();
    }

    public Double findMaxSalary() {
        Query query = em.createNamedQuery("FIND_MAX_SALARY");
        return (Double) query.getSingleResult();
    }
}
