package com.Robert;

import com.Robert.dao.GenericCRUDdao;
import com.Robert.dao.GenericCRUDdaoFactory;
import com.Robert.model.Company;
import com.Robert.model.DataItem;
import com.Robert.model.Person;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.*;

public class Main {

    public static final String DB_NAME = "testjava.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\Robert\\Desktop\\0001_Peter\\JavaSE\\GenericCRUD\\" + DB_NAME;

    public static final String TABLE_PEOPLE = "People";
    public static final String TABLE_COMPANIES = "Companies";

    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_SIZE = "size";

    public static final GenericCRUDdao daoJdbc = GenericCRUDdaoFactory.createJDBCDaoImplementation();

    public static void main(String[] args) {

        try (Connection conn = DriverManager
                .getConnection(CONNECTION_STRING);
             //conn.setAutoCommit(false);
             Statement statement = conn.createStatement()) {

            statement.execute("DROP TABLE IF EXISTS " + TABLE_PEOPLE);
            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_PEOPLE +
                    "(" +   COLUMN_NAME + " TEXT, " +
                            COLUMN_ID + " INTEGER, " +
                            COLUMN_PHONE + " INTEGER)");
            statement.execute("DROP TABLE IF EXISTS " + TABLE_COMPANIES);
            statement.execute("CREATE TABLE IF NOT EXISTS Companies" +
                    "(name TEXT, id INTEGER, size INTEGER)");

            statement.execute("INSERT INTO People (name, id, phone)" +
                    "VALUES('Joe', 1111, 9318557)");
            statement.execute("INSERT INTO People (name, id, phone)" +
                    "VALUES('Jane', 2222, 957)");
            statement.execute("INSERT INTO People (name, id, phone)" +
                    "VALUES('Anne', 333, 9423516)");

            insertDataItem(statement, new Company("Zeus"));
            statement.execute("UPDATE Companies SET size=80 WHERE name='Zeus' ");

            statement.execute("DROP TABLE IF EXISTS Foo");
            statement.execute("CREATE TABLE IF NOT EXISTS Foo" +
                    "(id INTEGER, name TEXT, size INTEGER, continent TEXT," +
                    "country TEXT, capital TEXT, inhabitants INTEGER, gdp INTEGER)");
            statement.execute("INSERT INTO Foo " +
                    "(id, name, size, continent, country, capital, inhabitants, gdp )" +
                " VALUES (65, 'Mine', 65, 'Europe', 'Hungray', 'Bpest', 9750000, 65)");

//            statement.execute("UPDATE People SET phone= 815668 WHERE name='Joe'");
//            statement.execute("DELETE FROM People WHERE name='Joe'");

            statement.execute("SELECT * FROM People");
            ResultSet results = statement.getResultSet();
//            ResultSet results = statement.executeQuery("SELECT * FROM People");
            while (results.next()) {
                System.out.println(results.getString("name") + " " +
                                   results.getInt("id") + " " +
                                   results.getInt("phone"));
            }
            results.close();

//            statement.close();
//            conn.close();

        } catch (SQLException e) {
            System.out.println("Something went wrong with the connection. " + e.getMessage());
        }

        InMemoryDataBase<Person> myDb = new InMemoryDataBase<>();

        Person me = new Person("Joe");
        myDb.createRecord(me);
        myDb.readRecord("Joe");


        myDb.updateRecord(me, "Joe Smith");
        myDb.deleteRecord(me);

        InMemoryDataBase<Company> companies = new InMemoryDataBase<>();

        Company myComp = new Company("Steel Works");
        companies.createRecord(myComp);
        companies.readRecord(myComp);

        FileOutputStream fout = null;
        try {
            fout = new FileOutputStream("file.txt");
            ObjectOutputStream oot = new ObjectOutputStream(fout);
            oot.writeObject(me);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


//
//        //illustrating close()
//        oot.close();


    }

    private static void insertDataItem(Statement statement, DataItem recordToInsert ) throws SQLException {
        if (recordToInsert instanceof Person) {
            Person record = (Person) recordToInsert;
            statement.execute("INSERT INTO " + TABLE_PEOPLE +
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
    }
}
