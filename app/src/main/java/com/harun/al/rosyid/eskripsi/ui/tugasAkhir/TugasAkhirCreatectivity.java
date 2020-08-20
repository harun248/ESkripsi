package com.harun.al.rosyid.eskripsi.ui.tugasAkhir;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.harun.al.rosyid.eskripsi.R;
import com.harun.al.rosyid.eskripsi.main.HomeActivity;
import com.harun.al.rosyid.eskripsi.model.Ta;

public class TugasAkhirCreatectivity extends AppCompatActivity {

    private DatabaseReference database;

    private Button btnSubmit;

    private EditText tahun;
    private EditText nim;
    private EditText nama;
    private EditText judul;
    private EditText prodi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ta);


        tahun = (EditText) findViewById(R.id.thn_ajar);
        nim = (EditText) findViewById(R.id.nim);
        nama = (EditText) findViewById(R.id.nama);
        judul = (EditText) findViewById(R.id.title);
        prodi = (EditText) findViewById(R.id.prodi);
        btnSubmit = (Button) findViewById(R.id.submit);

        database = FirebaseDatabase.getInstance().getReference();

        final Ta ta = (Ta) getIntent().getSerializableExtra("data");

        if (ta != null) {
            nama.setText(ta.getNama());
            nim.setText(ta.getNim());
            tahun.setText(ta.getTahun());
            judul.setText(ta.getJudul());
            prodi.setText(ta.getProdi());
            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ta.setNama(nama.getText().toString());
                    ta.setNim(nim.getText().toString());
                    ta.setTahun(tahun.getText().toString());
                    ta.setJudul(judul.getText().toString());
                    ta.setProdi(prodi.getText().toString());


                    updateTa(ta);
                }
            });
        } else{btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isEmpty(tahun.getText().toString()) && !isEmpty(nim.getText().toString()) && !isEmpty(nama.getText().toString()) && !isEmpty(judul.getText().toString()) && !isEmpty(prodi.getText().toString())) {
                    submitTa(new Ta(tahun.getText().toString(), nim.getText().toString(), nama.getText().toString(), judul.getText().toString(), prodi.getText().toString()));
                    startActivity(new Intent(TugasAkhirCreatectivity.this, HomeActivity.class));
                    Toast.makeText(getApplicationContext(), "Submit Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Snackbar.make(findViewById(R.id.submit), "Data tidak boleh kosong", Snackbar.LENGTH_LONG).show();

                    InputMethodManager imm = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(
                            nama.getWindowToken(), 0);
                }
            }
        });

    }
    }
    private boolean isEmpty(String s) {

        return TextUtils.isEmpty(s);
    }

    private void updateTa(Ta ta) {
        database.child("ta")
                .child(ta.getKey())
                .setValue(ta)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Snackbar.make(findViewById(R.id.submit), "Update successfully", Snackbar.LENGTH_LONG).setAction("Oke", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                finish();
                            }
                        }).show();
                    }
                });

    }



    private void submitTa(Ta ta) {

        database.child("ta").push().setValue(ta).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                tahun.setText("");
                nim.setText("");
                nama.setText("");
                judul.setText("");
                prodi.setText("");
                Snackbar.make(findViewById(R.id.submit), "Data berhasil ditambahkan", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    public static Intent getActIntent(Activity activity) {
        // kode untuk pengambilan Intent
        return new Intent(activity, TugasAkhirCreatectivity.class);
    }

}

