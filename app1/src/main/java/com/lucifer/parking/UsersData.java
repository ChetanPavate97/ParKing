package com.lucifer.parking;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class UsersData extends AppCompatActivity {

    SQLiteHelper sqLiteHelper;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    ListAdapter listAdapter ;
    ListView LISTVIEW;

    ArrayList<String> ID_Array;
    ArrayList<String> NAME_Array;
    ArrayList<String> PHONE_NUMBER_Array;
    ArrayList<String> EMAIL_ARRAY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_data);

        LISTVIEW = (ListView) findViewById(R.id.listView1);
        ID_Array = new ArrayList<String>();
        NAME_Array = new ArrayList<String>();
        PHONE_NUMBER_Array = new ArrayList<String>();
        EMAIL_ARRAY = new ArrayList<String>();
        sqLiteHelper = new SQLiteHelper(this);

    }

    @Override
    protected void onResume() {
        ShowSQLiteDBdata() ;
        super.onResume();
    }

    private void ShowSQLiteDBdata() {
        sqLiteDatabase = sqLiteHelper.getWritableDatabase();
        cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+SQLiteHelper.TABLE_NAME+"", null);
        ID_Array.clear();
        NAME_Array.clear();
        PHONE_NUMBER_Array.clear();
        EMAIL_ARRAY.clear();

        if (cursor.moveToFirst()) {
            do {
                ID_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_ID)));
                NAME_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_1_Name)));
                PHONE_NUMBER_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_2_PhoneNumber)));
                EMAIL_ARRAY.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_3_email)));
            } while (cursor.moveToNext());
        }
        listAdapter = new ListAdapter(UsersData.this,
                ID_Array,
                NAME_Array,
                PHONE_NUMBER_Array,
                EMAIL_ARRAY
        );
        LISTVIEW.setAdapter(listAdapter);
        cursor.close();
    }
}