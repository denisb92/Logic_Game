package com.example.denis.assignment3.model;

import android.provider.BaseColumns;

/**
 * Created by denis on 3/27/2017.
 */

public class AccountContracts {


    private AccountContracts() {}

    /* Inner class that defines the table contents */
    public static class AccountEntry implements BaseColumns {

        public static final String _ID = "id";
        public static final String COL_USERNAME = "username";
        public static final String COL_PASS = "password";
        public static  String COL_POINTS = "points";




    }
    public static final String TABLE_NAME = "Registration_table";


    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + "( " +
                    AccountEntry._ID + " INTEGER PRIMARY KEY, " +
                    AccountEntry.COL_USERNAME + " TEXT, " +
                    AccountEntry.COL_PASS + " TEXT, " +
                    AccountEntry.COL_POINTS + " INTEGER DEFAULT 0)";




    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

}


