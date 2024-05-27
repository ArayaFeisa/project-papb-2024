package com.example.kioskita;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditActivityFragment extends Fragment {
    private TextInputEditText inputEditJudul, inputEditBab, inputEditDeskripsi;
    private Button btnSave, btnUpload;
    private DatabaseReference databaseReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_edit_activity, container, false);

        inputEditJudul = rootView.findViewById(R.id.inputEditJudul);
        inputEditBab = rootView.findViewById(R.id.inputEditBab);
        inputEditDeskripsi = rootView.findViewById(R.id.inputEditDeskripsi);
        btnSave = rootView.findViewById(R.id.btn_save);
        btnUpload = rootView.findViewById(R.id.btn_upload);

        databaseReference = FirebaseDatabase.getInstance().getReference("materi");

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

        return rootView;
    }

    private void saveData() {
        String judul = inputEditJudul.getText().toString().trim();
        String bab = inputEditBab.getText().toString().trim();
        String deskripsi = inputEditDeskripsi.getText().toString().trim();

        if (!judul.isEmpty() && !bab.isEmpty() && !deskripsi.isEmpty()) {
            Tasks materi = new Tasks(judul, bab, deskripsi);

            databaseReference.push().setValue(materi)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(getContext(), "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(getContext(), "Gagal menyimpan data", Toast.LENGTH_SHORT).show();
                    });
        } else {
            Toast.makeText(getContext(), "Harap isi semua data", Toast.LENGTH_SHORT).show();
        }
    }
}

