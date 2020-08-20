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
import com.harun.al.rosyid.eskripsi.main.HomeActivity;
import com.harun.al.rosyid.eskripsi.R;


public class LoginActivity extends AppCompatActivity {

    private EditText email, password;
    private Button SignIn, SignUp;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        login();


        Button SignUp = (Button) findViewById(R.id.BtnSignUp);
        SignUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);

            }
        });
        Button Reset = (Button) findViewById(R.id.BtnReset);
        Reset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, ResetActivity.class);
                startActivity(i);

            }
        });

    }

    private void login() {

        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String emailUser = email.getText().toString().trim();
                final String passwordUser = password.getText().toString().trim();

                if (emailUser.isEmpty()) {
                    email.setError("Email tidak boleh kosong");
                } else if (!Patterns.EMAIL_ADDRESS.matcher(emailUser).matches()) {
                    email.setError("Email tidak valid");
                } else if (passwordUser.isEmpty()) {
                    password.setError("Password tidak boleh kosong");
                } else if (passwordUser.length() < 6) {
                    password.setError("Password minimal terdiri dari 6 karakter");
                } else {
                    auth.signInWithEmailAndPassword(emailUser, passwordUser)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (!task.isSuccessful()) {
                                        Toast.makeText(LoginActivity.this,
                                                "Sign in Failed " + task.getException().getMessage()
                                                , Toast.LENGTH_LONG).show();
                                    } else {
                                        Bundle bundle = new Bundle();
                                        bundle.putString("email", emailUser);
                                        bundle.putString("pass", passwordUser);
                                        startActivity(new Intent(LoginActivity.this, HomeActivity.class)
                                                .putExtra("emailpass", bundle));
                                        finish();
                                        Toast.makeText(getApplicationContext(), "Sign Successfully", Toast.LENGTH_SHORT).show();
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
        SignIn = findViewById(R.id.BtnSignIn);
        SignUp = findViewById(R.id.BtnSignUp);
        auth = FirebaseAuth.getInstance();
    }
}


