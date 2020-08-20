package com.harun.al.rosyid.eskripsi.ui.bimbingan;

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

public class BimbinganFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_bimbingan, container, false);



        Button add = (Button) root.findViewById(R.id.BtnAdd);

        ((Button) root.findViewById(R.id.BtnAdd)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                BimbinganFragment.this.getActivity().startActivity(new Intent(BimbinganFragment.this.getActivity().getBaseContext(), BimbinganCreateActivity.class));
                Toast.makeText(BimbinganFragment.this.getContext(), "Pengajuan Jadwal Bimbingan", 0).show();
            }
        });

        Button views = (Button) root.findViewById(R.id.BtnView);

        ((Button) root.findViewById(R.id.BtnView)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                BimbinganFragment.this.getActivity().startActivity(new Intent(BimbinganFragment.this.getActivity().getBaseContext(), BimbinganReadActivity.class));
                Toast.makeText(BimbinganFragment.this.getContext(), "Lihat Jadwal Bimbingan", 0).show();
            }
        });
        return root;
    }
}