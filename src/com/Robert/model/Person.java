package com.Robert.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Person extends DataItem {

    private static final String TABLE = "People";

    private int telephone;

    public Person(String name) {
        super(name);
    }

    @Override
    public String getTableName() {
        return TABLE;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> result = super.toMap();
        result.put("telephone", telephone);
        return result;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return telephone == person.telephone;
    }

    @Override
    public int hashCode() {
        return Objects.hash(telephone);
    }
}
