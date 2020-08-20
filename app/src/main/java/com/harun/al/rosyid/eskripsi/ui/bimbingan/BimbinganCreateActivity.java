package com.harun.al.rosyid.eskripsi.ui.bimbingan;

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
import com.harun.al.rosyid.eskripsi.model.Bimbingan;

public class BimbinganCreateActivity extends AppCompatActivity  {

    private DatabaseReference database;

    private Button btnSubmit;


    private EditText nim;
    private EditText nama;
    private EditText tanggal;
    private EditText tempat;
    private EditText pertemuan;
    private EditText dosen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bimbingan);


        tanggal = (EditText) findViewById(R.id.tgl);
        nim = (EditText) findViewById(R.id.nim);
        nama = (EditText) findViewById(R.id.nama);
        tempat = (EditText) findViewById(R.id.tempat);
        pertemuan = (EditText) findViewById(R.id.bimbing);

        dosen = (EditText) findViewById(R.id.dosen);
        btnSubmit = (Button) findViewById(R.id.submit);

        database = FirebaseDatabase.getInstance().getReference();

        final Bimbingan bimbingan = (Bimbingan) getIntent().getSerializableExtra("data");

        if (bimbingan != null) {
            nama.setText(bimbingan.getNama());
            nim.setText(bimbingan.getNim());
            tempat.setText(bimbingan.getTempat());
            tanggal.setText(bimbingan.getTanggal());
            pertemuan.setText(bimbingan.getPertemuan());
            dosen.setText(bimbingan.getDosen());
            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    bimbingan.setNama(nama.getText().toString());
                    bimbingan.setNim(nim.getText().toString());
                    bimbingan.setTempat(tempat.getText().toString());
                    bimbingan.setTanggal(tanggal.getText().toString());
                    bimbingan.setPertemuan(pertemuan.getText().toString());
                    bimbingan.setDosen(dosen.getText().toString());


                    updateBimbingan(bimbingan);
                }
            });
        } else { btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isEmpty(tanggal.getText().toString()) && !isEmpty(nim.getText().toString()) && !isEmpty(nama.getText().toString()) && !isEmpty(tempat.getText().toString()) && !isEmpty(pertemuan.getText().toString())&& !isEmpty(dosen.getText().toString())) {
                    submitBimbingan(new Bimbingan(tanggal.getText().toString(), nim.getText().toString(), nama.getText().toString(), tempat.getText().toString(), pertemuan.getText().toString(), dosen.getText().toString()));
                    startActivity(new Intent(BimbinganCreateActivity.this, HomeActivity.class));
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


    private void updateBimbingan(Bimbingan bimbingan) {
        database.child("bimbingan")
                .child(bimbingan.getKey())
                .setValue(bimbingan)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Snackbar.make(findViewById(R.id.submit), "Data berhasil diupdatekan", Snackbar.LENGTH_LONG).setAction("Oke", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                finish();
                            }
                        }).show();
                    }
                });

    }



    private void submitBimbingan(Bimbingan bimbingan) {

        database.child("bimbingan").push().setValue(bimbingan).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                tanggal.setText("");
                nim.setText("");
                nama.setText("");
                tempat.setText("");
                pertemuan.setText("");
                Snackbar.make(findViewById(R.id.submit), "Submit Successfully", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    public static Intent getActIntent(Activity activity) {
        // kode untuk pengambilan Intent
        return new Intent(activity, BimbinganCreateActivity.class);
    }


}