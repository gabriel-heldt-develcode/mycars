package com.example.mycars.view;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mycars.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FormRegister extends AppCompatActivity {

    private EditText editName;
    private EditText editEmail;
    private EditText editPassword;
    private Button buttonRegister;
    private Button buttonBack;
    public String[] message = {"Preencha todos os campos", "Cadastro realizado com sucesso!"};
    public String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_register);

        getSupportActionBar().hide();
        startComponents();

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editName.getText().toString();
                String email = editEmail.getText().toString();
                String password = editPassword.getText().toString();

                if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(view, message[0], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                } else {
                    registerUser(email, password, view);
                }
            }
        });
    }

    private void registerUser(String email, String password, View v) {

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    saveDataUser();

                    Snackbar snackbar = Snackbar.make(v, message[1], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                } else {
                    String error;
                    try {
                        throw task.getException();
                    } catch (FirebaseAuthWeakPasswordException e) {
                        error = "A senha precisa ter no mínimo 6 caracteres";
                    } catch (FirebaseAuthUserCollisionException e) {
                        error = "Essa conta já foi cadastrada";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        error = "Email inválido";
                    } catch (Exception e) {
                        error = "Erro ao cadstrar usuário";
                    }
                    Snackbar snackbar = Snackbar.make(v, error, Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }
            }
        });
    }

    private void saveDataUser() {
        String name = editName.getText().toString();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> user = new HashMap<>();
        user.put("name", name);

        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DocumentReference documentReference = db.collection("Users").document(userId);
        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d("db", "Sucesso ao salvar os dados");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("db_error", "Erro ao salvar os dados" + e.toString());
            }
        });
    }

    private void startComponents() {
        editName = findViewById(R.id.edit_name);
        editEmail = findViewById(R.id.edit_email);
        editPassword = findViewById(R.id.edit_password);
        buttonRegister = findViewById(R.id.button_register);
        buttonBack = findViewById(R.id.button_back);
    }
}