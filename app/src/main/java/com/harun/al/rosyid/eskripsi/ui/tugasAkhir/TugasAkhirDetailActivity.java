package com.harun.al.rosyid.eskripsi.ui.tugasAkhir;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.harun.al.rosyid.eskripsi.R;
import com.harun.al.rosyid.eskripsi.main.HomeActivity;
import com.harun.al.rosyid.eskripsi.model.Ta;

public class TugasAkhirDetailActivity extends AppCompatActivity {


    private TextView nim;
    private TextView nama;
    private TextView tahun;
    private TextView judul;
    private TextView prodi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tugas_akhir_detail);

        Button home = (Button) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(TugasAkhirDetailActivity.this, HomeActivity.class);
                startActivity(i);

            }
        });

        nim =(TextView)findViewById(R.id.nim);
        nama =(TextView)findViewById(R.id.nama);
        tahun =(TextView)findViewById(R.id.tahun);
        judul =(TextView)findViewById(R.id.judul);
        prodi =(TextView)findViewById(R.id.prodi);

        Ta ta = (Ta) getIntent().getSerializableExtra("data");
        if (ta!=null){
            nim.setText(ta.getNim());
            nama.setText(ta.getNama());
            tahun.setText(ta.getTahun());
            judul.setText(ta.getJudul());
            prodi.setText(ta.getProdi());
        }

    }
    public static Intent getActIntent(Activity activity){
        return new Intent(activity, TugasAkhirDetailActivity.class);
    }
}
