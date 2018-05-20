package com.kaishengit.entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerTest {

    @Test
    public void Shuchu(){
        System.out.println("你好");
    }

    @Test
    public void test(){
        Customer customer = new Customer();
        increment(customer);
        System.out.println(customer);
    }

    public void increment(Customer customer){
        customer.setId(customer.getId()+1);
    }

}