package com.example.kioskita2;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterHome extends RecyclerView.Adapter<AdapterHome.ViewHolder> {
    private ArrayList<itemEvent> dataSet;

    public AdapterHome(ArrayList<itemEvent> data) {
        this.dataSet = data;
    }

    @NonNull
    @Override
    public AdapterHome.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_event, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        itemEvent itemEvent = dataSet.get(position);

        holder.title.setText(itemEvent.getJudul());
        holder.kategori.setText(itemEvent.getKategori());
        holder.picKelas.setImageResource(itemEvent.getEventpic());

        holder.deleteButton.setOnClickListener(v -> {
            AppDatabase db = AppDatabase.getDatabase(v.getContext());
            new Thread(() -> {
                db.itemEventDao().delete(itemEvent);
                dataSet.remove(position);

                // Use Handler to run code on the main thread
                new Handler(Looper.getMainLooper()).post(this::notifyDataSetChanged);
            }).start();
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title, kategori;
        public ImageView picKelas;
        public Button deleteButton;

        public ViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            kategori = view.findViewById(R.id.tv_kategori);
            picKelas = view.findViewById(R.id.pic_kelas);
            deleteButton = view.findViewById(R.id.delete_button);
        }
    }
}






//public class AdapterHome extends RecyclerView.Adapter<AdapterHome.ViewHolder> {
//    ArrayList<itemEvent> dataItem;
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        TextView textJudul;
//        TextView textKategori;
//        ImageView EventPic;
//
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            textJudul = itemView.findViewById(R.id.title);
//            textKategori = itemView.findViewById(R.id.tv_kategori);
//            EventPic = itemView.findViewById(R.id.pic_kelas);
//        }
//    }
//    AdapterHome(ArrayList<itemEvent> data){
//        this.dataItem = data;
//    }
//    @NonNull
//    @Override
//    public AdapterHome.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_event, parent,false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull AdapterHome.ViewHolder holder, int position) {
//        TextView text_judul = holder.textJudul;
//        TextView text_kategori = holder.textKategori;
//        ImageView image_eventpic = holder.EventPic;
//
//        text_judul.setText(dataItem.get(position).getJudul());
//        text_kategori.setText(dataItem.get(position).getKategori());
//        image_eventpic.setImageResource(dataItem.get(position).getKelaspic());
//    }
//
//    @Override
//    public int getItemCount() {
//        return dataItem.size();
//    }
//
//
//}
