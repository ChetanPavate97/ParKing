package com.lucifer.parking;


import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Vehicle4 extends AppCompatActivity {
    CardView slot1Card, slot2Card, slot3Card, slot4Card;
    String date, time,  type, slot, duration;
    SQLiteDatabase db;
    SharedPreferences sharedPreferences;
    String username,fcost;
    Button estimate,pay;
    TextView ecost;
    int cost=0;
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle4);
        Intent intent = getIntent();
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_APPEND);
        username = sharedPreferences.getString("username", "");
        if(!username.isEmpty()) {
            date = intent.getStringExtra("date");
            time = intent.getStringExtra("time");
            type = intent.getStringExtra("type");
            duration = intent.getStringExtra("duration");

            Log.d("print slot",time);
            Log.d("print slot",duration);
            Log.d("print slot",type);
            Log.d("print slot",username);
        }

        slot1Card = findViewById(R.id.slot11Card);
        slot2Card = findViewById(R.id.slot12Card);
        slot3Card = findViewById(R.id.slot13Card);
        slot4Card = findViewById(R.id.slot14Card);
        estimate = findViewById(R.id.estimates);
        ecost = findViewById(R.id.ecost);
        pay = findViewById(R.id.pay);

        slot1Card.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                slot = "slot5";
                int confirm = updateSlot(date, time, duration, username, type, slot);
                if(confirm == 1){
                    slot1Card.setBackgroundColor(Color.GREEN);
                    estimate.setVisibility(View.VISIBLE);
                    slot2Card.setEnabled(false);
                    slot3Card.setEnabled(false);
                    slot4Card.setEnabled(false);
                } else {
                    slot1Card.setBackgroundColor(Color.RED);
                }
            }
        });
        slot2Card.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                slot = "slot6";
                int confirm = updateSlot(date, time, duration, username, type, slot);
                if(confirm == 1){
                    slot2Card.setBackgroundColor(Color.GREEN);
                    estimate.setVisibility(View.VISIBLE);
                    slot1Card.setEnabled(false);
                    slot3Card.setEnabled(false);
                    slot4Card.setEnabled(false);
                } else {
                    slot2Card.setBackgroundColor(Color.RED);
                }
            }
        });
        slot3Card.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                slot = "slot7";
                int confirm = updateSlot(date, time, duration, username, type, slot);
                if(confirm == 1){
                    slot3Card.setBackgroundColor(Color.GREEN);
                    estimate.setVisibility(View.VISIBLE);
                    slot2Card.setEnabled(false);
                    slot1Card.setEnabled(false);
                    slot4Card.setEnabled(false);
                } else {
                    slot3Card.setBackgroundColor(Color.RED);
                }
            }
        });
        slot4Card.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                slot = "slot8";
                int confirm = updateSlot(date, time, duration, username, type, slot);
                if(confirm == 1){
                    slot4Card.setBackgroundColor(Color.GREEN);
                    estimate.setVisibility(View.VISIBLE);
                    slot2Card.setEnabled(false);
                    slot3Card.setEnabled(false);
                    slot1Card.setEnabled(false);
                } else {
                    slot4Card.setBackgroundColor(Color.RED);
                }
            }
        });

        estimate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int edur = Integer.parseInt(duration);
                cost = edur*2*10;
                ecost.setText(Float.toString(cost)+" Rupees");
                pay.setVisibility(View.VISIBLE);

            }
        });

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username==null)
                    return;
                Intent intentHome=new Intent(getApplicationContext(),Payment.class);
                fcost = Integer.toString(cost);
                intentHome.putExtra("UserName",username);
                intentHome.putExtra("cost",fcost);
                startActivity(intentHome);
            }
        });
    }



    @SuppressLint("WrongConstant")
    public int updateSlot(String date, String time, String duration, String username, String type, String slot){
        try {
            db = openOrCreateDatabase("Bookin", SQLiteDatabase.CREATE_IF_NECESSARY, null);
            db.execSQL("CREATE TABLE book2 (id integer PRIMARY KEY AUTOINCREMENT, date text, time text , duration text , uname text , type text , slot text )");
        } catch (Exception e) {
            e.printStackTrace();
        }

        ContentValues values = new ContentValues();
        //db=openOrCreateDatabase("Bookin", SQLiteDatabase.CREATE_IF_NECESSARY,null);
        // Cursor c= db.rawQuery("SELECT  *  FROM parking WHERE username = ?"+username,null);
        Log.d("print slot",slot);
        Log.d("print slot",time);
        Log.d("print slot",duration);
        Log.d("print slot",type);
        Log.d("print slot",username);
        Cursor c = db.rawQuery("SELECT * FROM book2 WHERE date = ? and slot =? ", new String[]{date, slot});

        int d = 0;
        while (!c.isAfterLast()) {
            d++;
            c.moveToNext();
        }
        if (d == 0) {
            values.put("date", date);
            values.put("time", time);
            values.put("duration", duration);
            values.put("uname", username);
            values.put("type", type);
            values.put("slot", slot);
            if ((db.insert("book2", null, values)) != -1) {
                Toast.makeText(Vehicle4.this, "Selected " + slot, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(Vehicle4.this, "Error. Try again", Toast.LENGTH_LONG).show();
            }
            return 1;
        } else {
            Toast.makeText(Vehicle4.this, "Sorry. Slot Already Booked try another user", Toast.LENGTH_LONG).show();
            return -1;
        }
    }


}
