package com.harun.al.rosyid.eskripsi.auth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.harun.al.rosyid.eskripsi.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText email, password;
    private Button SignUp;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
        registerUser();


    }


    private void registerUser() {
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emailUser = email.getText().toString().trim();
                String passwordUser = password.getText().toString().trim();

                if (emailUser.isEmpty()) {
                    email.setError("Email tidak boleh kosong");
                } else if (!Patterns.EMAIL_ADDRESS.matcher(emailUser).matches()) {
                    email.setError("Email tidak valid");
                } else if (passwordUser.isEmpty()) {
                    password.setError("Password tidak boleh kosong");
                } else if (passwordUser.length() < 6) {
                    password.setError("Password minimal terdiri dari 6 karakter");
                } else {

                    auth.createUserWithEmailAndPassword(emailUser, passwordUser)
                            .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (!task.isSuccessful()) {
                                        Toast.makeText(RegisterActivity.this,
                                                "Register gagal karena " + task.getException().getMessage(),
                                                Toast.LENGTH_LONG).show();
                                    } else {

                                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                        Toast.makeText(getApplicationContext(), "Register Successfully", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }

    private void initView() {
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        SignUp = findViewById(R.id.BtnSignUp);
        auth = FirebaseAuth.getInstance();
    }
}


