package com.Robert.dao;

import com.Robert.dao.impl.GenericCRUDdaoJdbcImpl;
import com.Robert.model.DataItem;

public class GenericCRUDdaoFactory { //<T extends DataItem> {

    public static GenericCRUDdao/*<T>*/ createJDBCDaoImplementation() {
        return new GenericCRUDdaoJdbcImpl();
    }
}
