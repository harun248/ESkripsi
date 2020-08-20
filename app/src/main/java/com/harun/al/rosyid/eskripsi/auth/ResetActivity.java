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
import com.google.firebase.auth.FirebaseAuth;
import com.harun.al.rosyid.eskripsi.R;

public class ResetActivity extends AppCompatActivity {

    private EditText Email;
    private Button Reset;
    private FirebaseAuth auth;
    private String getEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        Email = findViewById(R.id.email);
        Reset = findViewById(R.id.BtReset);
        auth = FirebaseAuth.getInstance();

        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEmail = Email.getText().toString().trim();

                if (getEmail.isEmpty()) {
                    Email.setError("Email tidak boleh kosong");
                } else if (!Patterns.EMAIL_ADDRESS.matcher(getEmail).matches()) {
                    Email.setError("Email tidak valid");
                } else {
                    auth.sendPasswordResetEmail(getEmail)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()) {
                                        startActivity(new Intent(ResetActivity.this, LoginActivity.class));
                                        Toast.makeText(ResetActivity.this, "Check your email", Toast.LENGTH_SHORT).show();
                                        finish();
                                    } else {
                                        startActivity(new Intent(ResetActivity.this, LoginActivity.class));
                                        Toast.makeText(ResetActivity.this, "Reset Password Failed", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                }
            }
        });
    }
}
