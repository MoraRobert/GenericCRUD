package com.Robert.dao;

import com.Robert.model.DataItem;

import java.sql.SQLException;
import java.sql.Statement;

public interface GenericCRUDdao<T extends DataItem> {

    void insertDataItem(Statement statement, T recordToInsert ) throws SQLException;
    void printDataItem() throws SQLException;
    void close();

}
