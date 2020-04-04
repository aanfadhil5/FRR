package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText UsernameText;
    EditText PasswordText;
    EditText PasswordCText;
    Button RegisterButton;
    TextView TextViewLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);
        UsernameText = (EditText)findViewById(R.id.UsernameEditText);
        PasswordText = (EditText)findViewById(R.id.PasswordEditText);
        PasswordCText = (EditText)findViewById(R.id.Password_C_EditText);
        RegisterButton = (Button) findViewById(R.id.RegisterButton);
        TextViewLogin = (TextView) findViewById(R.id.textview_login);
        TextViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LoginIntent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(LoginIntent);
            }
        });
        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = UsernameText.getText().toString().trim();
                String password = PasswordText.getText().toString().trim();
                String cnf_pwd = PasswordCText.getText().toString().trim();



                if (password.equals(cnf_pwd)){
                    long val = db.addUser(username, password);
                    if (val > 0)
                    {
                        Toast.makeText(RegisterActivity.this, "DONE", Toast.LENGTH_SHORT).show();
                        Intent moveToLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(moveToLogin);
                    }
                    else
                    {
                        Toast.makeText(RegisterActivity.this, "Registration ERROR", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
