package com.example.kioskitalocal;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class EditActivityFragment extends Fragment {

    private TextInputEditText inputEditJudul;
    private Button btnSave, btnUpload;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_edit_activity, container, false);

        inputEditJudul = rootView.findViewById(R.id.inputEditJudul);
        btnSave = rootView.findViewById(R.id.btn_save);
        btnUpload = rootView.findViewById(R.id.btn_upload);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String judul = inputEditJudul.getText().toString();

                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("judul", judul);
                startActivity(intent);
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pindah ke PilihGambarMateriActivity
                Intent intent = new Intent(getActivity(), PilihGambarMateriActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
