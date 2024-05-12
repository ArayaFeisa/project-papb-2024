package com.example.kioskita;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class PreviewMateri extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_materi);


    }

    @Override
    protected void onResume() {
        super.onResume();
        ConstraintLayout mainLayout = findViewById(R.id.mainPrevLayout);
        Intent i = this.getIntent();
        String judul = i.getStringExtra("judul");
        String bab = i.getStringExtra("bab");
        String deskripsi = i.getStringExtra("deskripsi");
        TextView previewJudul = mainLayout.findViewById(R.id.txtPrevJudul);
        TextView previewBab = mainLayout.findViewById(R.id.txtPrevBab);
        TextView previewDeskripsi = mainLayout.findViewById(R.id.txtPrevDesc);
        String gambar = i.getStringExtra("gambar");
        ImageView previewGambar = mainLayout.findViewById(R.id.imgPrev);
        if (gambar != null) {
            Uri imageUri = Uri.parse(gambar);
            previewGambar.setImageURI(imageUri);
        }
        previewJudul.setText(judul);
        previewBab.setText(bab);
        previewDeskripsi.setText(deskripsi);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void back(View view) {
        Intent retuntIntent = new Intent();
        retuntIntent.putExtra("key", "visited");
        this.setResult(RESULT_OK, retuntIntent);

        finish();
    }
}