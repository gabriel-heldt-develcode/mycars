package com.example.mycars.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mycars.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FormLogin extends AppCompatActivity {

    private TextView text_register;
    private TextView text_forgot_password;
    private AppCompatButton button_login;
    private EditText editEmail;
    private EditText editPassword;
    private ProgressBar progressBar;
    public String[] message = {"Preencha todos os campos"};

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
                String email = editEmail.getText().toString();
                String password = editPassword.getText().toString();

                if (email.isEmpty() || password.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(view, message[0], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                } else {
                    authenticateUser(email, password, view);
                }
            }
        });
    }

    private void authenticateUser(String email, String password, View v) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    progressBar.setVisibility(View.VISIBLE);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            gotoNavActivity();
                        }
                    }, 2500);
                } else {
                    String error;
                    try {
                        throw task.getException();
                    } catch (Exception e) {
                        error = "Login Inválido";
                    }
                    Snackbar snackbar = Snackbar.make(v, error, Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }
            }
        });
    }

    private void gotoNavActivity() {
        startActivity(new Intent(getApplicationContext(), NavActivity.class));
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser userLoged = FirebaseAuth.getInstance().getCurrentUser();
        if (userLoged != null) {
            gotoNavActivity();
        }
    }

    private void startComponents() {
        text_register = findViewById(R.id.text_register);
        text_forgot_password = findViewById(R.id.text_forgot_password);
        button_login = findViewById(R.id.button_login);
        editEmail = findViewById(R.id.edit_email);
        editPassword = findViewById(R.id.edit_password);
        progressBar = findViewById(R.id.progressbar);
    }
}