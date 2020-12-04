package com.lucifer.parking;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class CC extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    String username;
    TextView welcomeText;
    CardView newBook, viewDetails, viewBook, cancelBook, feedBack, logout;
    @SuppressLint({"WrongConstant", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cc);

        welcomeText = findViewById(R.id.welcomeText);
        newBook = findViewById(R.id.newBooking);
        viewDetails = findViewById(R.id.details);
        viewBook = findViewById(R.id.viewBook);
        cancelBook = findViewById(R.id.cencelBook);
        feedBack = findViewById(R.id.feedback);
        logout = findViewById(R.id.logout);

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_APPEND);
        username = sharedPreferences.getString("username", "");
        welcomeText.setText("Welcome, "+username);
        newBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome=new Intent(getApplicationContext(),NewBooking.class);
                startActivity(intentHome);
            }
        });
        viewBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username==null)
                    return;
                Intent intentHome=new Intent(getApplicationContext(),ViewBooking.class);
                intentHome.putExtra("UserName",username);
                startActivity(intentHome);
            }
        });
        viewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username==null)
                    return;
                Intent intentHome=new Intent(getApplicationContext(),MyDetails2.class);
                intentHome.putExtra("UserName",username);
                startActivity(intentHome);
            }
        });
        feedBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome=new Intent(getApplicationContext(),Create.class);
                startActivity(intentHome);
            }
        });
        cancelBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome=new Intent(getApplicationContext(),Cancel.class);
                startActivity(intentHome);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intentHome);
            }
        });
    }
}