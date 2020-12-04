package com.lucifer.parking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class AdminHome extends AppCompatActivity {
    CardView userData, allBooking, currentBooking,adminLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        userData = findViewById(R.id.userD);
        allBooking = findViewById(R.id.allB);
        currentBooking = findViewById(R.id.curB);
        adminLogout = findViewById(R.id.adminLogout);

        userData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminHome.this, UsersData.class);
                startActivity(intent);
            }
        });
        allBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHome.this, AllBookings.class);
                startActivity(intent);
            }
        });
        currentBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHome.this, CurrentBookings.class);
                startActivity(intent);
            }
        });

        adminLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intentHome);
            }
        });
    }
}
