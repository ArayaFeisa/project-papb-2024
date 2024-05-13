package com.example.kioskita;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MateriBaruFragment extends Fragment {

    ImageButton btnPickImage;
    ImageView imageView;
    ActivityResultLauncher<Intent> resultLauncher;
    Uri selectedImageUri;
    String previewVisited = "";
    Button btSubmit, btPreviewMateri, btPilihMateri;
    MateriDatabase materiDatabase;
    EditText etJudul, etBab, etDeskripsi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_materi_baru, container, false);

        RoomDatabase.Callback myCallback = new RoomDatabase.Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
            }

            @Override
            public void onDestructiveMigration(@NonNull SupportSQLiteDatabase db) {
                super.onDestructiveMigration(db);
            }
        };
        materiDatabase = Room.databaseBuilder(requireContext(), MateriDatabase.class, "Materi")
                .addCallback(myCallback).build();

        btnPickImage = view.findViewById(R.id.ibUpload);
        imageView = view.findViewById(R.id.ibUpload);
        btSubmit = view.findViewById(R.id.btSubmit);
        etJudul = view.findViewById(R.id.etJudulMateri);
        etBab = view.findViewById(R.id.etBab);
        btPilihMateri = view.findViewById(R.id.btPilihMateri);
        btPreviewMateri = view.findViewById(R.id.btPreviewMateri);
        etDeskripsi = view.findViewById(R.id.etDeskripsi);
        registerResult();

        btnPickImage.setOnClickListener(view1 -> pickImage());
        btPreviewMateri.setOnClickListener(view1 -> preview());
        btPilihMateri.setOnClickListener(view1 -> pilih());

        btSubmit.setOnClickListener(v -> {
            try {
                String judul = etJudul.getText().toString();
                String bab = etBab.getText().toString();
                String gambar = selectedImageUri.toString();
                String deskripsi = etDeskripsi.getText().toString();

                MateriModel materi = new MateriModel(judul, bab, gambar, deskripsi);

                addMateriBackground(materi);
                requireActivity().finish();
            } catch (NullPointerException e) {
                Toast.makeText(requireContext(), "Harap isi data dan gambar dengan lengkap", Toast.LENGTH_SHORT).show();
            }



        });

        return view;
    }

    public void addMateriBackground(MateriModel materi) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executorService.execute(() -> {
            materiDatabase.getMateriDAO().addMateri(materi);

            handler.post(() -> Toast.makeText(requireContext(), "Berhasil menambahkan materi", Toast.LENGTH_SHORT).show());
        });
    }


    private void pickImage() {
        Intent intent = new Intent(MediaStore.ACTION_PICK_IMAGES);
        resultLauncher.launch(intent);
    }

    private void pilih() {
        Intent i = new Intent(requireContext(), PilihBab.class);
        startActivityForResult(i, 98);
    }

    private void preview() {
        Intent i = new Intent(requireContext(), PreviewMateri.class);
        String judul = etJudul.getText().toString();
        String bab = etBab.getText().toString();
        String deskripsi = etDeskripsi.getText().toString();
        if (selectedImageUri != null) {
            i.putExtra("gambar", selectedImageUri.toString());
        }
        i.putExtra("judul", judul);
        i.putExtra("bab", bab);
        i.putExtra("deskripsi", deskripsi);
        startActivityForResult(i, 99);
    }

    private void registerResult() {
        resultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        try {
                            Uri imageUri = result.getData().getData();
                            selectedImageUri = imageUri;
                            imageView.setImageURI(imageUri);
                        } catch (Exception e) {
                            Toast.makeText(requireContext(), "Tidak ada gambar terpilih", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }

//    public void submit() {
//        requireActivity().finish();
//    }

    @Override
    public void onResume() {
        super.onResume();
        if (previewVisited.equalsIgnoreCase("visited")) {
            Button previewButton = requireView().findViewById(R.id.btPreviewMateri);
            previewButton.setTextColor(Color.LTGRAY);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 99 && resultCode == getActivity().RESULT_OK) {
            String value = data.getStringExtra("key");
            previewVisited = value;
        } else if (requestCode == 98 && resultCode == getActivity().RESULT_OK) {
            EditText etBab = requireView().findViewById(R.id.etBab);
            String value = data.getStringExtra("bab");
            etBab.setText(value);
        }
    }

}
