package com.example.mycars.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mycars.R;

public class FormLogin extends AppCompatActivity {

    private TextView text_register;
    private TextView text_forgot_password;
    private AppCompatButton button_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);


        getSupportActionBar().hide();
        startComponents();

        text_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FormRegister.class);
                startActivity(intent);
            }
        });

        text_forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ForgotPassword.class);
                startActivity(intent);
            }
        });

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkdata();
                startActivity(new Intent(getApplicationContext(), NavActivity.class));
                finish();
            }
        });
    }

    private void checkdata() {
        EditText editEmail = findViewById(R.id.edit_email);
        EditText editPassword = findViewById(R.id.edit_password);
        String email = editEmail.getText().toString();
        String password = editPassword.getText().toString();

        if (email.equals("g@g.com") && password.equals("123")) {
            Toast.makeText(this, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Email ou senha incorretos", Toast.LENGTH_SHORT).show();
        }
    }

    private void startComponents() {
        text_register = findViewById(R.id.text_register);
        text_forgot_password = findViewById(R.id.text_forgot_password);
        button_login = findViewById(R.id.button_login);
    }
}