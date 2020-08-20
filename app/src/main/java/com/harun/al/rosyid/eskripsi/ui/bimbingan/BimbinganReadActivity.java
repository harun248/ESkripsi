package com.harun.al.rosyid.eskripsi.ui.bimbingan;

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
import com.harun.al.rosyid.eskripsi.Adapter.BimbinganAdapter;
import com.harun.al.rosyid.eskripsi.R;
import com.harun.al.rosyid.eskripsi.model.Bimbingan;

import java.util.ArrayList;

public class BimbinganReadActivity extends AppCompatActivity implements BimbinganAdapter.FirebaseDataListener{

    private DatabaseReference database;
    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Bimbingan> daftarBimbingan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bimbingan);

        rvView = (RecyclerView) findViewById(R.id.rv_main);
        rvView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvView.setLayoutManager(layoutManager);

        database = FirebaseDatabase.getInstance().getReference();

        database.child("bimbingan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                daftarBimbingan = new ArrayList<>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {

                    Bimbingan bimbingan = noteDataSnapshot.getValue(Bimbingan.class);
                    bimbingan.setKey(noteDataSnapshot.getKey());


                    daftarBimbingan.add(bimbingan);
                }

                adapter = new BimbinganAdapter(daftarBimbingan, BimbinganReadActivity.this);
                rvView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
            }
        });
    }

    public static Intent getActIntent(Activity activity){
        return new Intent(activity, BimbinganReadActivity.class);
    }
    @Override
    public void onDeleteData(Bimbingan bimbingan, final int position) {

        if(database!=null){
            database.child("bimbingan").child(bimbingan.getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(BimbinganReadActivity.this,"success delete", Toast.LENGTH_LONG).show();
                }
            });

        }
    }
}