package com.Robert;

import com.Robert.model.DataItem;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDataBase<T extends DataItem> {

    private String name;
    private List<T> list = new ArrayList<>();

    public void createRecord(T record) {
        if (!list.contains(record)) {
            list.add(record);
            System.out.println("Data item " + record.getName() + " recorded in database " );
        } else {
            System.out.println("Data item " + record.getName() + " is already in this database.");
        }
    }

    public T readRecord(T record) {
        if (list.contains(record)) {
            System.out.println("\nThe record you are reading is: \n" +
                    "Name: " + record.getName() +
                    "\nID: " + record.getId());
        } else {
            System.out.println("The record you intend to read is not in the database");
        }
        return record;
    }

    public T readRecord(String name) {


        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(name)) {
                System.out.println("Read OK. The record found is: " + name);
                return list.get(i);
            }
        }

        System.out.println("The record you wanted to read, " + name + ", is not in the database");
        return null;
    }

    public void updateRecord(T recordToUpdate, String name) {

        int index = findRecordIndexById(recordToUpdate.getId());
        if (index > -1) {
            list.set(index, recordToUpdate);
            System.out.print("\nRecord " + recordToUpdate.getName());
            recordToUpdate.setName(name);
            System.out.println(" successfully updated.\n" +
                    "The new name is: " + recordToUpdate.getName());
        } else {
            System.out.println("The record you intend to update is not in the database.");
        }
    }

    public int findRecordIndexById(long id) {
        for (DataItem record : list) {
            if (record.getId() == id) {
                return list.indexOf(record);
            }
        }
        return -1;
    }

    public void deleteRecord(T recordToDelete) {
        if (list.contains(recordToDelete)) {
            list.remove(list.indexOf(recordToDelete));
//            int index = findRecordIndex(recordToDelete);
//            list.remove(index);
            System.out.println("\nThe record " + recordToDelete.getName() + " has been successfully "
                    + "deleted from the database.");
        } else {
            System.out.println("The record you intend to delete is not in the database.");
        }
    }


    public int findRecordIndex (String record) {
        int r = -1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(name)) {
                r = i;
                break;
            }
        }
        if (r != -1) {
            return r;
        } else {
            System.out.println("The record you are searching for, " + name + ", is not in the database");
            return -1;
        }
    }

    public int findRecordIndex (T record) {
        return list.indexOf(record);
    }
}
