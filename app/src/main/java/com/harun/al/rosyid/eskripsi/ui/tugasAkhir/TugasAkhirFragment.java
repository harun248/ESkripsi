package com.harun.al.rosyid.eskripsi.ui.tugasAkhir;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.harun.al.rosyid.eskripsi.R;

public class TugasAkhirFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_tugas_akhir, container, false);


        Button add = (Button) root.findViewById(R.id.BtnAdd);



        ((Button) root.findViewById(R.id.BtnAdd)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                TugasAkhirFragment.this.getActivity().startActivity(new Intent(TugasAkhirFragment.this.getActivity().getBaseContext(), TugasAkhirCreatectivity.class));
                Toast.makeText(TugasAkhirFragment.this.getContext(), "Pengajuan Tugas Akhir", 0).show();
            }
        });

        Button views = (Button) root.findViewById(R.id.BtnView);

        ((Button) root.findViewById(R.id.BtnView)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                TugasAkhirFragment.this.getActivity().startActivity(new Intent(TugasAkhirFragment.this.getActivity().getBaseContext(), TugasAkhirReadActivity.class));
                Toast.makeText(TugasAkhirFragment.this.getContext(), "Lihat Status", 0).show();
            }
        });
        return root;



    }
}