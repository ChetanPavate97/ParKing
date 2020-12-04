package com.lucifer.parking;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class SQLiteHelper3 extends SQLiteOpenHelper {

    static String DATABASE_NAME="Bookin";

    public static final String TABLE_NAME="book2";

    public static final String Table_Column_ID="id";

    public static final String Table_Column_1_Date="date";

    public static final String Table_Column_2_Time="time";

    public static final String Table_Column_3_Duration="duration";

    public static final String Table_Column_4_Uname="uname";

    public static final String Table_Column_5_Type="type";

    public static final String Table_Column_6_Slot="slot";



    public SQLiteHelper3(Context context) {

        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        String CREATE_TABLE="CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ("+Table_Column_ID+" INTEGER PRIMARY KEY, "+Table_Column_1_Date+" VARCHAR, "+Table_Column_2_Time+" VARCHAR, "+Table_Column_3_Duration+" VARCHAR,"+Table_Column_4_Uname+" VARCHAR, "+Table_Column_5_Type+" VARCHAR,"+Table_Column_6_Slot+" VARCHAR)";
        database.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }

}