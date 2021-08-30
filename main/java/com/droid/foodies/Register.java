package com.droid.foodies;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    private TextView loginText;
    private EditText name, registerEmail, registerPassword;
    private Button registerBtn;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);
        registerBtn = (Button) findViewById(R.id.btnRegister);
        loginText = (TextView) findViewById(R.id.loginText);
        name  = (EditText) findViewById(R.id.name);
        registerEmail = (EditText)  findViewById(R.id.registerEmail);
        registerPassword = (EditText)  findViewById(R.id.registerPassword);

        registerBtn.setOnClickListener(v -> {
            registerUser();
            Toast.makeText(this, "Button Clicked", Toast.LENGTH_SHORT);
        });

        loginText.setOnClickListener(v -> {
            Intent intent = new Intent(Register.this, Login.class);
            startActivity(intent);
        });
    }

    private void registerUser() {
        String email = registerEmail.getText().toString().trim();
        String password = registerPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT);
            return;
        }

        if(TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT);
            return;
        }

        progressDialog.setMessage("Registering user...");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if(task.isSuccessful()) {
                        progressDialog.dismiss();
                        finish();
                        startActivity(new Intent(getApplicationContext(), Home.class));

                        Toast.makeText(this, "Registered user", Toast.LENGTH_SHORT);
                    } else {
                        Toast.makeText(this, "Unable to Registered user", Toast.LENGTH_SHORT);
                    }


                });
        Intent intent = new Intent(Register.this, MainActivity.class);
        startActivity(intent);
    }
}