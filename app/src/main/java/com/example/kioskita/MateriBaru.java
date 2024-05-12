package com.example.kioskita;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.view.View;
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
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MateriBaru extends AppCompatActivity {

    ImageButton btnPickImage;
    ImageView imageView;
    ActivityResultLauncher<Intent> resultLauncher;
    Uri selectedImageUri;
    String previewVisited = "";
    Button btSubmit;
    MateriDatabase materiDatabase;
    EditText etJudul, etBab, etDeskripsi;

    //tanpa fragment




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materi_baru);
        ConstraintLayout mainLayout = findViewById(R.id.mainLayout);

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
        materiDatabase = Room.databaseBuilder(getApplicationContext(), MateriDatabase.class, "Materi")
                .addCallback(myCallback).build();



        btnPickImage = mainLayout.findViewById(R.id.imageButton);
        imageView = mainLayout.findViewById(R.id.imageButton);
        btSubmit = mainLayout.findViewById(R.id.btSubmit);
        etJudul = mainLayout.findViewById(R.id.etJudul);
        etBab = mainLayout.findViewById(R.id.etBab);
        etDeskripsi = mainLayout.findViewById(R.id.etDeskripsi);
        registerResult();

        btnPickImage.setOnClickListener(view -> pickImage());

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String judul = etJudul.getText().toString();
                String bab = etBab.getText().toString();
                String gambar = selectedImageUri.toString();
                String deskripsi = etDeskripsi.getText().toString();

                MateriModel materi = new MateriModel(judul, bab, gambar, deskripsi);

                addMateriBackground(materi);
                finish();
            }
        });
    }

    public void addMateriBackground(MateriModel materi) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executorService.execute(new Runnable() {
            @Override
            public void run() {

                materiDatabase.getMateriDAO().addMateri(materi);


                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MateriBaru.this, "Berhasil menambahkan materi", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

    private void pickImage() {
        Intent intent = new Intent(MediaStore.ACTION_PICK_IMAGES);
        resultLauncher.launch(intent);

    }

    public void preview(View view) {
        ConstraintLayout mainLayout = findViewById(R.id.mainLayout);
        imageView = mainLayout.findViewById(R.id.imageButton);
        Intent i = new Intent(this, PreviewMateri.class);
        String judul = ((EditText) mainLayout.findViewById(R.id.etJudul)).getText().toString();
        String bab = ((EditText) mainLayout.findViewById(R.id.etBab)).getText().toString();
        String deskripsi = ((EditText) mainLayout.findViewById(R.id.etDeskripsi)).getText().toString();
        if (selectedImageUri != null) {
            i.putExtra("gambar", selectedImageUri.toString());
        }
        i.putExtra("judul", judul);
        i.putExtra("bab", bab);
        i.putExtra("deskripsi", deskripsi);
        startActivityForResult(i, 99);
    }

    public void pilih(View view) {
        Intent i = new Intent(MateriBaru.this, PilihBab.class);
        startActivityForResult(i, 98);
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
                            Toast.makeText(MateriBaru.this, "Tidak ada gambar terpilih", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (previewVisited.equalsIgnoreCase("visited")) {
            ConstraintLayout mainLayout = findViewById(R.id.mainLayout);
            Button preview =  mainLayout.findViewById(R.id.prevTampilan);
            preview.setTextColor(Color.LTGRAY);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 99 && resultCode == RESULT_OK) {
            String value = data.getStringExtra("key");
            previewVisited = value;
        } else if (requestCode == 98 && resultCode == RESULT_OK) {
            EditText etBab = findViewById(R.id.etBab);
            String value = data.getStringExtra("bab");
            etBab.setText(value);
        }
    }

    public void submit(View view) {
        finish();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void backToMain(View view) {
        finish();
    }
}