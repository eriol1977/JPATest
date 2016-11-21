/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jpatest.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table

@NamedQueries({
    @NamedQuery(query = "Select e from Employee e where e.ename = :name", name = "FIND_EMPLOYEE_BY_NAME"),
    @NamedQuery(query = "Select e from Employee e", name = "FIND_ALL_EMPLOYEES"),
    @NamedQuery(query = "Select e from Employee e where e.ename like :nameStart", name = "FIND_EMPLOYEES_BY_NAME_START"),
    @NamedQuery(query = "Select e from Employee e order by e.salary desc", name = "FIND_EMPLOYEES_BY_DESC_SALARY"),
    @NamedQuery(query = "Delete from Employee", name = "DELETE_ALL_EMPLOYEES"),
    @NamedQuery(query = "Select e from Employee e where e.salary between :lowest and :highest", name = "FIND_EMPLOYEES_IN_SALARY_RANGE"),
    @NamedQuery(query = "Select max(e.salary) from Employee e", name = "FIND_MAX_SALARY"),
})

public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int eid;
    private String ename;
    private double salary;
    private String deg;

    public Employee() {
    }

    public Employee(String ename, double salary, String deg) {
        this.ename = ename;
        this.salary = salary;
        this.deg = deg;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDeg() {
        return deg;
    }

    public void setDeg(String deg) {
        this.deg = deg;
    }

    @Override
    public String toString() {
        return "Employee [eid=" + eid + ", ename=" + ename + ", salary=" + salary + ", deg=" + deg + "]";
    }
}
