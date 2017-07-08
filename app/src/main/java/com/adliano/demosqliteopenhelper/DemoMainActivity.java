package com.adliano.demosqliteopenhelper;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Toast;

public class DemoMainActivity extends AppCompatActivity
{
    ContactDatabaseHelper databaseHelper;
    AppCompatEditText edUserName,edCity,edPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_main);

        // init the EditText for Text Inputs
        edUserName = (AppCompatEditText)findViewById(R.id.ed_userName_id);
        edCity = (AppCompatEditText)findViewById(R.id.ed_city_id);
        edPhoneNumber = (AppCompatEditText)findViewById(R.id.ed_phoneNumber_id);

        // Create the Database
        databaseHelper = new ContactDatabaseHelper(getApplicationContext());
    }

    // Action from the Load Button
    public void buttonLoadAction(View view)
    {
        if(String.valueOf(edUserName.getText()).isEmpty()
                && String.valueOf(edCity.getText()).isEmpty()
                && String.valueOf(edPhoneNumber.getText()).isEmpty())
        {
            Toast.makeText(getBaseContext(),R.string.check_fields,Toast.LENGTH_LONG).show();
        }
        else
        {
            //populate database by first getting the Writable database
            SQLiteDatabase database = databaseHelper.getWritableDatabase();
            // Create a map using the ContentValues and put data into it using  put(String key, Object value)
            // String key will be the Column name ,check documentations for available value params
            ContentValues contentValues = new ContentValues();
            contentValues.put(TableEntries.COLUMN_USER_NAME,String.valueOf(edUserName.getText()));
            contentValues.put(TableEntries.COLUMN_CITY,String.valueOf(edCity.getText()));
            contentValues.put(TableEntries.COLUMN_PHONE_NUMBER,String.valueOf(edPhoneNumber.getText()));

            // Insert the new row, returning the primary key value of the new row
            long newRowId = database.insert(TableEntries.TABLE_NAME,null,contentValues);
        }
    }
}
