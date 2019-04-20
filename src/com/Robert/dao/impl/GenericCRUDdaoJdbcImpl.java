package com.Robert.dao.impl;

import com.Robert.dao.GenericCRUDdao;
import com.Robert.model.Company;
import com.Robert.model.DataItem;
import com.Robert.model.Person;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.Map;

public class GenericCRUDdaoJdbcImpl<T extends DataItem> implements GenericCRUDdao<T> {

    private Connection conn;
    public static final String DB_NAME = "testjava.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:" +
            "\\Users\\Robert\\Desktop\\0001_Peter\\JavaSE\\GenericCRUD\\" + DB_NAME;

    public static final String TABLE_PEOPLE = "People";
    public static final String TABLE_COMPANIES = "Companies";

    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_SIZE = "size";

    Statement statement;

    public GenericCRUDdaoJdbcImpl() {
        try {
            Connection conn = DriverManager
                    .getConnection(CONNECTION_STRING);
            //conn.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("Something went wrong with the connection. " + e.getMessage());
        }
    }


    @Override
    public void insertDataItem(Statement statement, T recordToInsert) throws SQLException {

        String sql = "INSERT INTO " + recordToInsert.getTableName() + mapToSqlSegment(recordToInsert.toMap());
        statement.execute(sql);

       /*
        if (recordToInsert instanceof Person) {
            Person record = (Person) recordToInsert;
            statement.execute("INSERT INTO " + recordToInsert.getTableName() +
                    " (" + COLUMN_NAME + ", " +
                    COLUMN_ID + ", " +
                    COLUMN_PHONE  +
                    " )" +
                    "VALUES('" + record.getName()+ "', "+ record.getId() + ", '" +
                    record.getTelephone() + "')");
        } else {
            Company record = (Company) recordToInsert;
            statement.execute("INSERT INTO " + TABLE_COMPANIES +
                    " (" + COLUMN_NAME + ", " +
                    COLUMN_ID + ", " +
                    COLUMN_SIZE  +
                    " )" +
                    "VALUES('" + record.getName()+ "', "+ record.getId() + ", '" +
                    record.getSize() + "')");
        }
        */
    }

    @Override
    public void printDataItem() throws SQLException {        //TODO: implement for Companies too

        ResultSet results = statement.executeQuery("SELECT * FROM People");
        while (results.next()) {
            System.out.println(results.getString("name") + " " +
                    results.getInt("id") + " " +
                    results.getInt("phone"));
        }
        results.close();
    }

    @Override
    public void close() {
        try {
            conn.close();
            statement.close();
        } catch (SQLException ex) {
            throw new IllegalStateException("Couldn't close connection", ex);
        }
    }

    private String mapToSqlSegment(Map<String, Object> map) {

        StringBuilder propertyNamesSql = new StringBuilder("(");
        StringBuilder propertyValuesSql = new StringBuilder(" VALUES (");
        for(Map.Entry<String, Object> entry : map.entrySet()) {
            //TODO: complete SQL generation algprithm
            propertyNamesSql.append("'");
            propertyNamesSql.append(entry.getKey());
            propertyValuesSql.append(entry.getValue());
        }
        return propertyNamesSql.toString() + propertyValuesSql.toString();
    }
}
