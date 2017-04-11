package com.example.denis.assignment3.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.denis.assignment3.model.AccountContracts.SQL_CREATE_ENTRIES;
import static com.example.denis.assignment3.model.AccountContracts.SQL_DELETE_ENTRIES;

/**
 * Created by denis on 3/27/2017.
 */

public class AccountDbHelper extends SQLiteOpenHelper{


        public static final int DATABASE_VERSION = 9;
        public static final String DATABASE_NAME = "Accounts.db";

        public AccountDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_ENTRIES);
        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        }
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }
    }

