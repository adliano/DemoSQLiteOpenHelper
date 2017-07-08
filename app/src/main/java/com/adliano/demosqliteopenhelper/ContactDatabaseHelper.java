package com.adliano.demosqliteopenhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by adliano on 7/7/17.
 * SQLiteOpenHelper
 */

public class ContactDatabaseHelper extends SQLiteOpenHelper
{
    private Context context;

    public ContactDatabaseHelper(Context context)
    {
        // will pass properties to super using the DatabaseEntries
        super(context, DatabaseEntries.DATABASE_NAME, null, DatabaseEntries.DATABASE_VERSION);
        this.context = context;
    }

    // Method Called to create DataBase
    @Override
    public void onCreate(SQLiteDatabase db)
    {

        String SQL_CREATE_TABLE = "CREATE TABLE "+ TableEntries.TABLE_NAME+
                "( "+ TableEntries._ID+" INTEGER PRIMARY KEY,"+
                TableEntries.COLUMN_USER_NAME+" TEXT,"+ TableEntries.COLUMN_CITY+
                " TEXT,"+ TableEntries.COLUMN_PHONE_NUMBER+" TEXT)";

        db.execSQL(SQL_CREATE_TABLE);
    }

    // Method called to upgrade DataBase
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        // here you need to create the query to first drop the table and after call onCreate
        /* ******* THIS WILL DELETE ALL DATA ****** */
        String dropTable = "DROP TABLE IF EXISTS "+ TableEntries.TABLE_NAME;
        db.execSQL(dropTable);
        onCreate(db);
    }
}
