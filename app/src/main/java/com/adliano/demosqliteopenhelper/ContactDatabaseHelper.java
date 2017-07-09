package com.adliano.demosqliteopenhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by adliano on 7/7/17.
 * SQLiteOpenHelper, used to create , upgrade and downgrade database
 */

class ContactDatabaseHelper extends SQLiteOpenHelper
{
    ContactDatabaseHelper(Context context)
    {
        // will pass properties to super using the DatabaseEntries
        super(context, DatabaseEntries.DATABASE_NAME, null, DatabaseEntries.DATABASE_VERSION);
    }

    // Method Called to create DataBase
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(DatabaseEntries.TableEntries.SQL_CREATE_TABLE);
    }

    // Method called to upgrade DataBase
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        // here you need to create the query to first drop the table and after call onCreate
        /* ******* THIS WILL DELETE ALL DATA ****** */
        String dropTable = "DROP TABLE IF EXISTS "+ DatabaseEntries.TableEntries.TABLE_NAME;
        db.execSQL(dropTable);
        onCreate(db);
    }
}
