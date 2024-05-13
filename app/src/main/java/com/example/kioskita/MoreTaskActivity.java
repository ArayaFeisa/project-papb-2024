package com.example.kioskita;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MoreTaskActivity extends AppCompatActivity {
    private List<Tasks> data;
    private RecyclerView rvTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_task);

        this.data = new ArrayList<Tasks>();

        Tasks a = new Tasks("Manajemen Waktu", "Cari tahu bagaimana cara manajemen waktu");
        Tasks b = new Tasks("Atur Pengeluaran", "Berikut cara mengatur keuangan");

        this.data.add(a); this.data.add(a); this.data.add(a);
        this.data.add(b); this.data.add(b); this.data.add(b);

        this.rvTask = this.findViewById(R.id.rv_task);

        ListAdapter listAdapter = new ListAdapter(MoreTaskActivity.this, this.data);

        RecyclerView.LayoutManager lm = new LinearLayoutManager(MoreTaskActivity.this);
        this.rvTask.setLayoutManager(lm);
        this.rvTask.setAdapter(listAdapter);
    }
}