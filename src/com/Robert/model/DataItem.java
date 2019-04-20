package com.Robert.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public abstract class DataItem implements Serializable {

    private final long id;
    private String name;

    public DataItem(String name) {
        this.name = name;
        this.id = ThreadLocalRandom.current().nextInt(1000, 2000);
    }

//    private DataItem findR(String customerName) {
//        for (int i = 0; i < customers.size(); i++) {
//            Customer checkedCustomer = this.customers.get(i);
//            if (checkedCustomer.getCustomerName().equals(customerName)) {
//                return checkedCustomer;
//            }
//        }
//        return null;
//    }


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract String getTableName();

    public Map<String, Object> toMap() {
        Map<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("name", name);
        return result;
    }


}
