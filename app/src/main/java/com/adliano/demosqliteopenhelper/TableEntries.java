package com.adliano.demosqliteopenhelper;

import android.provider.BaseColumns;

/**
 * Created by adliano on 7/8/17.
 * This class will only hold the Contact Info Table properties
 * Default constructor set to private to prevent any init
 * implementing the BaseColumns interface, your inner class can inherit a primary key field called  _ID
 * that some Android classes such as cursor adaptors will expect it to have.
 * It's not required, but this can help your database work harmoniously with the Android framework.
 */

public class TableEntries implements BaseColumns
{
    private TableEntries()
    {
        // set to private to prevent any try of instantiate of this class
    }

    public static final String TABLE_NAME = "contact_info_table";
    public static final String COLUMN_USER_NAME = "user_name";
    public static final String COLUMN_CITY = "city";
    public static final String COLUMN_PHONE_NUMBER = "phone_number";
}
