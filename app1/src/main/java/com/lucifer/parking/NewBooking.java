package com.lucifer.parking;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class NewBooking extends AppCompatActivity {
    static int count=1;
    Button search;
    SQLiteDatabase db;

    EditText dateEt, timeEt, durationEt;
    String dateStr, timeStr, typeStr, durStr ,username ;
    Spinner vehicle;
    SharedPreferences sharedPreferences;
    int year, month, dayOfMonth;
    int hour, minute;
    Calendar calendar;
    Calendar c;
    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_booking);

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_APPEND);
        username = sharedPreferences.getString("username", "");


        search = findViewById(R.id.Button1);
        dateEt = findViewById(R.id.dateEt);
        timeEt = findViewById(R.id.timeEt);
        durationEt = findViewById(R.id.durationEt);
        vehicle = findViewById(R.id.typeSpinner);


        calendar = Calendar.getInstance();


        dateEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {



                if (hasFocus) {
                    year = calendar.get(Calendar.YEAR);
                    month = calendar.get(Calendar.MONTH);
                    dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                    datePickerDialog = new DatePickerDialog(NewBooking.this,
                            new DatePickerDialog.OnDateSetListener() {
                                @SuppressLint("SetTextI18n")
                                @Override
                                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                    dateEt.setText(day + "/" + (month + 1) + "/" + year);
                                }
                            }, year, month, dayOfMonth);
                    datePickerDialog.setTitle("Select date");
                    datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                    datePickerDialog.show();
                }
            }
        });
        timeEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus) {

                    hour = calendar.get(Calendar.HOUR_OF_DAY);
                    minute = calendar.get(Calendar.MINUTE);
                    timePickerDialog = new TimePickerDialog(NewBooking.this,
                            new TimePickerDialog.OnTimeSetListener() {
                                @SuppressLint("SetTextI18n")
                                @Override
                                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                        timeEt.setText(hourOfDay + ":" + minute);
                                }
                            }, hour, minute, false);
                    timePickerDialog.setTitle("Select time");
                    timePickerDialog.show();
                }

            }
        });



        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateStr = dateEt.getText().toString();
                timeStr = timeEt.getText().toString();
                durStr = durationEt.getText().toString();
                typeStr = vehicle.getSelectedItem().toString();
                if (dateStr.matches("") || timeStr.matches("") || durStr.matches("")) {
                    Toast.makeText(NewBooking.this, "Please Enter All The Details", Toast.LENGTH_SHORT).show();
                    return;}


                updateDatatoDB(dateStr, timeStr, durStr, typeStr, username);
            }
        });



    }


    @SuppressLint("WrongConstant")
    private void updateDatatoDB(String dateStr, String timeStr, String durStr, String typeStr , String username) {
        try {
            db=openOrCreateDatabase("Booking",SQLiteDatabase.CREATE_IF_NECESSARY,null);
            db.execSQL("CREATE TABLE book1 (id integer PRIMARY KEY AUTOINCREMENT, date text, time text , duration text , uname text , type text )");
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        ContentValues values = new ContentValues();
        values.put("date", dateStr);
        values.put("time", timeStr);
        values.put("duration", durStr);
        values.put("uname", username );
        values.put("type", typeStr);

        if ((db.insert("book1", null, values)) != -1) {
            Toast.makeText(NewBooking.this, "Inserted...", Toast.LENGTH_LONG).show();
            if(typeStr.equals("2 wheeler")){
                Intent i = new Intent(NewBooking.this, vehicle.class);
                i.putExtra("type",typeStr);
                i.putExtra("date",dateStr);
                i.putExtra("time",timeStr);
                i.putExtra("duration",durStr);
                i.putExtra("username",username);
                startActivity(i);
            }
            else {
                Intent in = new Intent(NewBooking.this, Vehicle4.class);
                in.putExtra("type",typeStr);
                in.putExtra("date",dateStr);
                in.putExtra("time",timeStr);
                in.putExtra("duration",durStr);
                in.putExtra("username",username);
                startActivity(in);
            }

        } else {
            Toast.makeText(NewBooking.this, "Error...", Toast.LENGTH_LONG).show();
        }
    }
}
