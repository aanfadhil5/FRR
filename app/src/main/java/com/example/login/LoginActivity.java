package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
        EditText UsernameText;
        EditText PasswordText;
        Button LoginButton;
        TextView TextViewRegister;
        DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DatabaseHelper(this);
        UsernameText = (EditText)findViewById(R.id.UsernameEditText);
        PasswordText = (EditText)findViewById(R.id.PasswordEditText);
        LoginButton = (Button) findViewById(R.id.LoginButton);
        TextViewRegister = (TextView) findViewById(R.id.textview_register);
        TextViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(registerIntent);
            }
        });

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = UsernameText.getText().toString().trim();
                String password = PasswordText.getText().toString().trim();
                Boolean res = db.checkUser(user, password);
                if (res == true) {
                    Intent HomePage = new Intent(LoginActivity.this,HomeActivity.class);
                    startActivity(HomePage);
                }
                else {
                    Toast.makeText(LoginActivity.this,"User Belum Terdaftar", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
