package com.harun.al.rosyid.eskripsi.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.harun.al.rosyid.eskripsi.R;
import com.harun.al.rosyid.eskripsi.model.Bimbingan;
import com.harun.al.rosyid.eskripsi.ui.bimbingan.BimbinganCreateActivity;
import com.harun.al.rosyid.eskripsi.ui.bimbingan.BimbinganDetailActivity;
import com.harun.al.rosyid.eskripsi.ui.bimbingan.BimbinganReadActivity;

import java.util.ArrayList;

public class BimbinganAdapter extends RecyclerView.Adapter<BimbinganAdapter.ViewHolder> {
    FirebaseDataListener listener;
    private ArrayList<Bimbingan> daftarBimbingan;
    private Context context;


    public BimbinganAdapter(ArrayList<Bimbingan> bimbingans, Context ctx) {
        daftarBimbingan = bimbingans;
        context = ctx;
        listener = (BimbinganReadActivity) ctx;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView pertemuan, tanggal, tempat;
        private CardView card;

        ViewHolder(View v) {
            super(v);
            pertemuan = (TextView) v.findViewById(R.id.pertemuan);
            tanggal = (TextView) v.findViewById(R.id.tanggal);
            tempat = (TextView) v.findViewById(R.id.tempat);
            card = (CardView) v.findViewById(R.id.card);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bimbingan, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final String pertemuan = daftarBimbingan.get(position).getPertemuan();
        final String tanggal = daftarBimbingan.get(position).getTanggal();
        final String tempat = daftarBimbingan.get(position).getTempat();
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(BimbinganDetailActivity.getActIntent((Activity) context).putExtra("data", daftarBimbingan.get(position)));

            }
        });
        holder.card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_view);
                dialog.setTitle("Action");
                dialog.show();

                Button editButton = (Button) dialog.findViewById(R.id.btnEdit);
                Button delButton = (Button) dialog.findViewById(R.id.btnDelete);


                editButton.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                                context.startActivity(BimbinganCreateActivity.getActIntent((Activity) context).putExtra("data", daftarBimbingan.get(position)));
                            }
                        }
                );

                delButton.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                                listener.onDeleteData(daftarBimbingan.get(position), position);
                            }
                        }
                );
                return true;
            }
        });
        holder.pertemuan.setText(pertemuan);
        holder.tanggal.setText(tanggal);
        holder.tempat.setText(tempat);
    }

    @Override
    public int getItemCount() {

        return daftarBimbingan.size();
    }

    public interface FirebaseDataListener {
        void onDeleteData(Bimbingan bimbingan, int position);
    }

}
