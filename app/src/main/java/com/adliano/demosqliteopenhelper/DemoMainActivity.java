package com.adliano.demosqliteopenhelper;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adliano Alves on 7/7/17.
 *
 * MainActivity of the project
 */

public class DemoMainActivity extends AppCompatActivity
{
    ContactDatabaseHelper databaseHelper;
    AppCompatEditText edUserName,edCity,edPhoneNumber;
    AppCompatTextView textViewOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_main);

        // init the EditText for Text Inputs
        edUserName = (AppCompatEditText)findViewById(R.id.ed_userName_id);
        edCity = (AppCompatEditText)findViewById(R.id.ed_city_id);
        edPhoneNumber = (AppCompatEditText)findViewById(R.id.ed_phoneNumber_id);

        // init the TextView for output texts
        textViewOutput = (AppCompatTextView)findViewById(R.id.tv_outputText_id);

        // Create the Database
        databaseHelper = new ContactDatabaseHelper(getApplicationContext());
    }

    // Action from the Load Button
    public void buttonLoadAction(View view)
    {
        if(!String.valueOf(edUserName.getText()).isEmpty()
                && !String.valueOf(edCity.getText()).isEmpty()
                && !String.valueOf(edPhoneNumber.getText()).isEmpty())
        {
            //populate database by first getting the Writable database
            SQLiteDatabase database = databaseHelper.getWritableDatabase();
            // Create a map using the ContentValues and put data into it using  put(String key, Object value)
            // String key will be the Column name ,check documentations for available value params
            ContentValues contentValues = new ContentValues();
            contentValues.put(DatabaseEntries.TableEntries.COLUMN_USER_NAME,String.valueOf(edUserName.getText()));
            contentValues.put(DatabaseEntries.TableEntries.COLUMN_CITY,String.valueOf(edCity.getText()));
            contentValues.put(DatabaseEntries.TableEntries.COLUMN_PHONE_NUMBER,String.valueOf(edPhoneNumber.getText()));

            // Insert the new row, returning the primary key value of the new row
            long newRowId = database.insert(DatabaseEntries.TableEntries.TABLE_NAME,null,contentValues);

            getSQLiteData();
        }
        else  Toast.makeText(getBaseContext(),R.string.check_fields,Toast.LENGTH_LONG).show();
    }

    private void getSQLiteData()
    {
        // get the SQLite database
        // for a secure usage of this method use it on background thread like IntentService
        SQLiteDatabase database = databaseHelper.getReadableDatabase();

        // populate a array with the fields that you want to query
        String[] columnsToReturn = {DatabaseEntries.TableEntries.COLUMN_USER_NAME, DatabaseEntries.TableEntries.COLUMN_PHONE_NUMBER};

        // sorted ?
        String sortOrder = DatabaseEntries.TableEntries.COLUMN_USER_NAME + " DESC";

        Cursor cursor = database.query(
                DatabaseEntries.TableEntries.TABLE_NAME, // table name
                columnsToReturn,                         // Columns to return
                null,                                    // Columns for the WHERE clause
                null,                                    // Values for the WHERE clause
                null,                                    // Group the rows
                null,                                    // Filter by row groups
                sortOrder);                               // sort order

        // Populate to the TextView
        String toSet = "";
        List usersInfo = new ArrayList<>();
        while(cursor.moveToNext())
        {
            toSet += cursor.getString(cursor.getColumnIndexOrThrow(DatabaseEntries.TableEntries.COLUMN_USER_NAME))+
            "\t"+cursor.getString(cursor.getColumnIndexOrThrow(DatabaseEntries.TableEntries.COLUMN_PHONE_NUMBER))+"\n";
        }

        textViewOutput.setText(toSet);

        cursor.close();
    }

    // Action from the Clear Button
    public void buttonClear(View view)
    {
        // get the SQLite database
        // for a secure usage of this method use it on background thread like IntentService
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        database.delete(DatabaseEntries.TableEntries.TABLE_NAME,null,null);
        textViewOutput.setText(null);
    }
}
