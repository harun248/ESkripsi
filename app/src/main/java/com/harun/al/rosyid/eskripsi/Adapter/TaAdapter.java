package com.harun.al.rosyid.eskripsi.Adapter;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.harun.al.rosyid.eskripsi.R;
import com.harun.al.rosyid.eskripsi.model.Ta;
import com.harun.al.rosyid.eskripsi.ui.tugasAkhir.TugasAkhirCreatectivity;
import com.harun.al.rosyid.eskripsi.ui.tugasAkhir.TugasAkhirDetailActivity;
import com.harun.al.rosyid.eskripsi.ui.tugasAkhir.TugasAkhirReadActivity;

import java.util.ArrayList;

public class TaAdapter extends RecyclerView.Adapter<TaAdapter.ViewHolder> {

    FirebaseDataListener listener;

    private ArrayList<Ta> daftarTa;
    private Context context;

    public TaAdapter(ArrayList<Ta> tas, Context ctx){
        daftarTa= tas;
        context = ctx;
        listener = (TugasAkhirReadActivity)ctx;
    }
    class ViewHolder extends RecyclerView.ViewHolder {

        TextView judul;
        private TableLayout table;

        ViewHolder(View v) {
            super(v);
            judul = (TextView) v.findViewById(R.id.judul);

            table = (TableLayout) v.findViewById(R.id.table);
        }
    }

    @Override
    public TaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ta, parent, false);

        TaAdapter.ViewHolder vh = new TaAdapter.ViewHolder(v);
        return vh;
    }



    @Override
    public void onBindViewHolder(TaAdapter.ViewHolder holder, final int position) {

        final String judul = daftarTa.get(position).getJudul();
        holder.table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(TugasAkhirDetailActivity.getActIntent((Activity) context).putExtra("data", daftarTa.get(position)));

            }
        });
        holder.table.setOnLongClickListener(new View.OnLongClickListener() {
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
                                context.startActivity(TugasAkhirCreatectivity.getActIntent((Activity) context).putExtra("data", daftarTa.get(position)));
                            }
                        }
                );

                delButton.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                                listener.onDeleteData(daftarTa.get(position), position);
                            }
                        }
                );
                return true;
            }
        });
        holder.judul.setText(judul);

    }
    @Override
    public int getItemCount() {

        return daftarTa.size();
    }

    public interface FirebaseDataListener{
        void onDeleteData(Ta ta, int position);
    }
}
