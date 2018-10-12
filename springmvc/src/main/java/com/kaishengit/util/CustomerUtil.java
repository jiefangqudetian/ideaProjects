package com.kaishengit.util;

import com.kaishengit.entity.Customer;

public class CustomerUtil {

    public void refIncrement(Customer customer){
        customer.setId(customer.getId()+1);
        customer = new Customer(5,"jack","beijing");
    }

    public void simIncrement(int i){
        i++;
    }

    public void simmIncrement(Integer i){
        i++;
    }

    public void strIncrement(String string){
        string = string + "你好";
    }

    public void strbulIncrement(StringBuilder string){
        string = new StringBuilder("jack");
        string.append("+jack");
    }
}
