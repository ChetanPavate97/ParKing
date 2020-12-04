package com.lucifer.parking;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CurrentBookings extends AppCompatActivity {

    SQLiteHelper3 sqLiteHelper;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    ListAdapter3 listAdapter ;
    ListView LISTVIEW;

    ArrayList<String> ID_Array;
    ArrayList<String> DATE_Array;
    ArrayList<String> TIME_Array;
    ArrayList<String> DURATION_ARRAY;
    ArrayList<String> USERNAME_ARRAY;
    ArrayList<String> TYPE_ARRAY;
    ArrayList<String> SLOT_ARRAY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_data);

        LISTVIEW = (ListView) findViewById(R.id.listView1);

        ID_Array = new ArrayList<String>();

        DATE_Array = new ArrayList<String>();

        TIME_Array = new ArrayList<String>();

        DURATION_ARRAY = new ArrayList<String>();

        USERNAME_ARRAY = new ArrayList<String>();

        TYPE_ARRAY = new ArrayList<String>();

        SLOT_ARRAY = new ArrayList<String>();

        sqLiteHelper = new SQLiteHelper3(this);

    }

    @Override
    protected void onResume() {

        ShowSQLiteDBdata() ;

        super.onResume();
    }

    private void ShowSQLiteDBdata() {

        sqLiteDatabase = sqLiteHelper.getWritableDatabase();

        cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+SQLiteHelper3.TABLE_NAME+"", null);

        ID_Array.clear();
        DATE_Array.clear();
        TIME_Array.clear();
        DURATION_ARRAY.clear();
        USERNAME_ARRAY.clear();
        TYPE_ARRAY.clear();
        SLOT_ARRAY.clear();

        if (cursor.moveToFirst()) {
            do {

                ID_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper3.Table_Column_ID)));

                DATE_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper3.Table_Column_1_Date)));

                TIME_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper3.Table_Column_2_Time)));

                DURATION_ARRAY.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper3.Table_Column_3_Duration)));

                USERNAME_ARRAY.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper3.Table_Column_4_Uname)));

                TYPE_ARRAY.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper3.Table_Column_5_Type)));

                SLOT_ARRAY.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper3.Table_Column_6_Slot)));

            } while (cursor.moveToNext());
        }

        listAdapter = new ListAdapter3(CurrentBookings.this,

                ID_Array,
                DATE_Array,
                TIME_Array,
                DURATION_ARRAY,
                USERNAME_ARRAY,
                TYPE_ARRAY,
                SLOT_ARRAY

        );

        LISTVIEW.setAdapter(listAdapter);

        cursor.close();
    }
}