package com.example.kioskita;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter
        extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private final Context context;
    private final List<Tasks> task;

    public ListAdapter(Context context, List<Tasks> task){
        this.context = context;
        this.task = task;
    }

    public class VH extends RecyclerView.ViewHolder {
        private final TextView tv_taskTitle;
        private final TextView tv_taskDesc;
        public VH(@NonNull View itemView) {
            super(itemView);
            this.tv_taskTitle = itemView.findViewById(R.id.tv_taskTitle);
            this.tv_taskDesc = itemView.findViewById(R.id.tv_taskDesc);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vh = LayoutInflater.from(this.context).inflate(R.layout.row_task, parent, false);
        return new VH(vh);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Tasks task = this.task.get(position);
        VH vh = (VH) holder;
        vh.tv_taskTitle.setText(task.judul.toString());
        vh.tv_taskDesc.setText(task.desc.toString());
    }

    @Override
    public int getItemCount() {
        return this.task.size();
    }
}
