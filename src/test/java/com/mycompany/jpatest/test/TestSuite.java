/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jpatest.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
    TestEmployee.class,
    TestSingleTableStrategy.class,
    TestJoinedTableStrategy.class,
    TestTablePerClassStrategy.class,
    TestManyToOne.class,
    TestOneToMany.class,
    TestOneToOne.class,
    TestManyToMany.class
})

public class TestSuite {
    
}
