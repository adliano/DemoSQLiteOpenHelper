package com.adliano.demosqliteopenhelper;

import android.provider.BaseColumns;

/**
 * Created by Adliano Alves on 7/7/17.
 *
 * This class will only hold the Database properties
 * Default constructor set to private to prevent any init
 */

final class DatabaseEntries
{
    static final int DATABASE_VERSION = 1;
    static final String DATABASE_NAME = "contacts.db";

    private DatabaseEntries()
    {
        // set to private to prevent any try of instantiate of this class
        // This allows another class to access them without polluting its.
    }

    /**
     * Created by adliano on 7/8/17.
     * This class will only hold the Contact Info Table properties
     * Default constructor set to private to prevent any init
     * implementing the BaseColumns interface, your inner class can inherit a primary key field called  _ID
     * that some Android classes such as cursor adaptors will expect it to have.
     * It's not required, but this can help your database work harmoniously with the Android framework.
     */
    static class TableEntries implements BaseColumns
    {
        static final String TABLE_NAME = "contact_info_table";
        static final String COLUMN_USER_NAME = "user_name";
        static final String COLUMN_CITY = "city";
        static final String COLUMN_PHONE_NUMBER = "phone_number";
        // Query to create the Contact Table
        static final String SQL_CREATE_TABLE = "CREATE TABLE "+ TABLE_NAME +
                "( "+ DatabaseEntries.TableEntries._ID+" INTEGER PRIMARY KEY,"+
                COLUMN_USER_NAME+" TEXT,"+ COLUMN_CITY+" TEXT,"+ COLUMN_PHONE_NUMBER+" TEXT)";
        // Query to drop contact table
        static final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;
    }
}
