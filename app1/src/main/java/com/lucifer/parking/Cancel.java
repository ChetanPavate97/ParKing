package com.lucifer.parking;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Cancel extends AppCompatActivity {
    Button buttonDeleteBooking;
    EditText editTextBookID;
    SQLiteDatabase db;
    SQLiteOpenHelper d;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel);

        buttonDeleteBooking = (Button) findViewById(R.id.buttonDeleteBooking);
        editTextBookID = findViewById(R.id.editTextBookID);
        try {
            db = openOrCreateDatabase("Bookin", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        buttonDeleteBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = editTextBookID.getText().toString();
                try {
                    String d = "DELETE FROM book2 WHERE id=" + t;
                    db.execSQL(d);
                } catch (Exception e) {
                    // System.out.print("Error..................");
                }
                editTextBookID.setText("");
                Toast.makeText(Cancel.this, "Deleted...", Toast.LENGTH_LONG).show();
            }
        });
    }

}
