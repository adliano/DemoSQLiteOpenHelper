package com.adliano.demosqliteopenhelper;

/**
 * Created by Adliano Alves on 7/7/17.
 *
 * This class will only hold the Database properties
 * Default constructor set to private to prevent any init
 */

public class DatabaseEntries
{
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "contacts.db";

    private DatabaseEntries()
    {
        // set to private to prevent any try of instantiate of this class
    }
}
