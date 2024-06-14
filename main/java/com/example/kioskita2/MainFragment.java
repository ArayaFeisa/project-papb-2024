package com.example.kioskita2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
public class MainFragment extends Fragment {
    RecyclerView recyclerView;
    AdapterHome adapterHome;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<itemEvent> data;
    AppDatabase db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        recyclerView = view.findViewById(R.id.rvEvent);
        recyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);

        db = AppDatabase.getDatabase(getContext());

        // Load data from Room database
        new Thread(() -> {
            data = new ArrayList<>(db.itemEventDao().getAllItemEvents());
            getActivity().runOnUiThread(() -> {
                adapterHome = new AdapterHome(data);
                recyclerView.setAdapter(adapterHome);
            });
        }).start();

        return view;
    }
}







//public class MainFragment extends Fragment {
//    RecyclerView recyclerView;
//    AdapterHome adapterHome;
//    RecyclerView.LayoutManager layoutManager;
//    ArrayList<itemEvent> data;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.activity_main, container, false);
//
//        recyclerView = view.findViewById(R.id.rvEvent);
//        recyclerView.setHasFixedSize(true);
//
//        layoutManager = new GridLayoutManager(getContext(), 2);
//        recyclerView.setLayoutManager(layoutManager);
//
//        data = new ArrayList<>();
//        for (int i = 0; i < menuEvent.judulevent.length; i++) {
//            data.add(new itemEvent(
//                    menuEvent.judulevent[i],
//                    menuEvent.Kategori[i],
//                    menuEvent.eventpic[i]
//            ));
//        }
//        adapterHome = new AdapterHome(data);
//        recyclerView.setAdapter(adapterHome);
//
//        return view;
//    }
//}
