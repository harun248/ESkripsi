package com.harun.al.rosyid.eskripsi.ui.bimbingan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.harun.al.rosyid.eskripsi.R;
import com.harun.al.rosyid.eskripsi.main.HomeActivity;
import com.harun.al.rosyid.eskripsi.model.Bimbingan;

public class BimbinganDetailActivity extends AppCompatActivity {

    private TextView nim;
    private TextView nama;
    private TextView tanggal;
    private TextView tempat;
    private TextView pertemuan;

    private TextView dosen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bimbingan_detail);

        Button home = (Button) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(BimbinganDetailActivity.this, HomeActivity.class);
                startActivity(i);

            }
        });

        nim =(TextView)findViewById(R.id.nim);
        nama =(TextView)findViewById(R.id.nama);
        tanggal =(TextView)findViewById(R.id.tanggal);
        tempat =(TextView)findViewById(R.id.tempat);
        pertemuan =(TextView)findViewById(R.id.pertemuan);
        dosen =(TextView)findViewById(R.id.dosen);

        Bimbingan bimbingan = (Bimbingan) getIntent().getSerializableExtra("data");
        if (bimbingan!=null){
            nim.setText(bimbingan.getNim());
            nama.setText(bimbingan.getNama());
            tanggal.setText(bimbingan.getTanggal());
            tempat.setText(bimbingan.getTempat());
            pertemuan.setText(bimbingan.getPertemuan());
            dosen.setText(bimbingan.getDosen());
        }

    }
    public static Intent getActIntent(Activity activity){
        return new Intent(activity, BimbinganDetailActivity.class);
    }
}
