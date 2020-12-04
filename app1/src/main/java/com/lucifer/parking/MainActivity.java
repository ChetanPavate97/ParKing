package com.lucifer.parking;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    Button btnSignIn;
    TextView btnSignUp;
    EditText usernameInput, passwordInput;
    LoginDataBaseAdapter loginDataBaseAdapter;
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        // create a instance of SQLite Database
        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();

        btnSignIn = findViewById(R.id.buttonSignIN);
        btnSignUp = findViewById(R.id.buttonSignUP);
        usernameInput = findViewById(R.id.usernameInput);
        passwordInput = findViewById(R.id.passwordInput);

        // Set OnClick Listener on SignUp button
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SignUPActivity.class));
            }
        });
    }

    public void signIn(View V) {
        String userName = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();

        String storedPassword = loginDataBaseAdapter.getSinlgeEntry(userName);
        if(password.equals(storedPassword)) {
            Toast.makeText(MainActivity.this, "Congrats: Login Successfull", Toast.LENGTH_LONG).show();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("username", userName);
            editor.commit();
            Intent intentHome=new Intent(getApplicationContext(),CC.class);
            startActivity(intentHome);
        }
        else {
            Toast.makeText(MainActivity.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
        }
    }

    public void admin(View V) {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.admin);
        dialog.setTitle("Admin");
        final  EditText editTextUserName= dialog.findViewById(R.id.adminName);
        final  EditText editTextPassword= dialog.findViewById(R.id.adminPass);

        Button btnSignIn = (Button)dialog.findViewById(R.id.adminLogin);
        btnSignIn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String userName=editTextUserName.getText().toString();
                String password=editTextPassword.getText().toString();

                // fetch the Password form database for respective user name
                //String storedPassword=loginDataBaseAdapter.getSinlgeEntry(userName);
                // check if the Stored password matches with  Password entered by user
                if(userName.equals("admin")&&(password.equals("admin"))) {
                    Toast.makeText(MainActivity.this, "Congrats: Login Successfull", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                    Intent intentHome=new Intent(getApplicationContext(),AdminHome.class);
                    startActivity(intentHome);
                }
                else {
                    Toast.makeText(MainActivity.this, "Admin credentials does not match", Toast.LENGTH_LONG).show();
                }
            }
        });
        dialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close The Database
        loginDataBaseAdapter.close();
    }
}