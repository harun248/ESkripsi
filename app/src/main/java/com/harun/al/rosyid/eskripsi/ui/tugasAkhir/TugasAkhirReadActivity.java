package com.harun.al.rosyid.eskripsi.ui.tugasAkhir;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.harun.al.rosyid.eskripsi.Adapter.TaAdapter;
import com.harun.al.rosyid.eskripsi.R;
import com.harun.al.rosyid.eskripsi.model.Ta;

import java.util.ArrayList;

public class TugasAkhirReadActivity extends AppCompatActivity implements TaAdapter.FirebaseDataListener {

    private DatabaseReference database;
    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Ta> daftarTa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tugas_akhir);

        rvView = (RecyclerView) findViewById(R.id.rv_main);
        rvView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvView.setLayoutManager(layoutManager);

        database = FirebaseDatabase.getInstance().getReference();

        database.child("ta").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                daftarTa = new ArrayList<>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {

                    Ta ta = noteDataSnapshot.getValue(Ta.class);
                    ta.setKey(noteDataSnapshot.getKey());


                    daftarTa.add(ta);
                }


                adapter = new TaAdapter(daftarTa, TugasAkhirReadActivity.this);
                rvView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
            }
        });
    }

    public static Intent getActIntent(Activity activity){
        return new Intent(activity, TugasAkhirReadActivity.class);
    }

    @Override
    public void onDeleteData(Ta ta, final int position) {

        if(database!=null){
            database.child("ta").child(ta.getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(TugasAkhirReadActivity.this,"success delete", Toast.LENGTH_LONG).show();
            }
        });

        }
    }
}