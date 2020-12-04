package com.lucifer.parking;



import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Feedback extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        Button buttonSubmit = findViewById(R.id.buttonSubmit);
        final EditText editText = findViewById(R.id.editTextfb);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
                Toast.makeText(Feedback.this, "Feedback successfully inserted", Toast.LENGTH_LONG).show();
            }
        });
    }
}
