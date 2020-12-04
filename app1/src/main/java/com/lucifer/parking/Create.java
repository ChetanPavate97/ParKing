package com.lucifer.parking;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Create extends Activity {
    EditText editTextUserName,editTextEmailid,editTextPhoneno;
    Button btnCreateAccount;
    SQLiteDatabase db;
    static int count=2;
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_information);
        btnCreateAccount=(Button)findViewById(R.id.B11);
        editTextUserName=(EditText)findViewById(R.id.E1);
        editTextEmailid=(EditText)findViewById(R.id.E2);
        editTextPhoneno=(EditText)findViewById(R.id.E3);
        final Context context=this;
        try
        {
            db=openOrCreateDatabase("Parking",SQLiteDatabase.CREATE_IF_NECESSARY,null);
            db.execSQL("CREATE TABLE parking (id integer PRIMARY KEY AUTOINCREMENT, username text, email text , phoneno text)");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String s=editTextUserName.getText().toString();
                String s1=editTextEmailid.getText().toString();
                String s2=editTextPhoneno.getText().toString();
                //db.execSQL("INSERT INTO log VALUES (s)");

                ContentValues values=new ContentValues();
                count++;
                // values.put("id",Integer.toString(count));

                values.put("username",s);
                values.put("email",s1);
                values.put("phoneno",s2);
                if((db.insert("parking",null,values))!= -1)
                {
                    Toast.makeText(Create.this, "Inserted...",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(Create.this,"Error...",Toast.LENGTH_LONG).show();
                }
                editTextPhoneno.setText("");
                editTextUserName.setText("");
                editTextEmailid.setText("");
                //Intent i=new Intent(context,Admin.class);
                //startActivity(i);

            }
        });
    }
}