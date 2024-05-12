package com.example.kioskita;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BabAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
    private final List<JudulBab> babs;

    public BabAdapter(Context context, List<JudulBab> babs) {
        this.context = context;
        this.babs = babs;
    }

    public class VH extends RecyclerView.ViewHolder {
        private final TextView tvBab;
        private final TextView tvDeskripsi;
        private final ImageView ivIlustrasi;
        private final Button btPilih;

        public VH(@NonNull View itemView) {
            super(itemView);
            this.tvBab = itemView.findViewById(R.id.tvBab);
            this.tvDeskripsi = itemView.findViewById(R.id.tvDeskripsi);
            this.ivIlustrasi = itemView.findViewById(R.id.ivIlustrasi);
            this.btPilih = itemView.findViewById(R.id.btPilih);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vh = LayoutInflater.from(this.context).inflate(R.layout.row_sub, parent, false);
        return new VH(vh);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        JudulBab s = this.babs.get(position);
        VH vh = (VH) holder;
        vh.tvBab.setText(s.bab.toString());
        vh.tvDeskripsi.setText(s.deskripsi.toString());
        vh.ivIlustrasi.setImageResource(s.ilustrasi);
        vh.btPilih.setTag(s.bab.toString());
    }

    @Override
    public int getItemCount() {
        return this.babs.size();
    }
}
