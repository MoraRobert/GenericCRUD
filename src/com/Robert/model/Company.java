package com.Robert.model;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Company extends DataItem {

    private final long id;
    private int size;
    private static final String TABLE = "Companies";

    public Company(String name) {
        super(name);
        this.id = ThreadLocalRandom.current().nextLong(1000,9999);
    }

    @Override
    public long getId() {
        return super.getId();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public String getTableName() {
        return null;
    }

    @Override
    public Map<String, Object> toMap() {
        return super.toMap();
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return size == company.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(size);
    }
}
