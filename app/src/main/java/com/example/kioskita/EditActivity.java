package com.example.kioskita;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class EditActivity extends AppCompatActivity {

    private TextInputEditText inputEditJudul;
    private Button btnSave, btnUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        inputEditJudul = findViewById(R.id.inputEditJudul);
        btnSave = findViewById(R.id.btn_save);
        btnUpload = findViewById(R.id.btn_upload);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String judul = inputEditJudul.getText().toString();

                Intent intent = new Intent(EditActivity.this, MainActivity.class);
                intent.putExtra("judul", judul);
                startActivity(intent);
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pindah ke PilihGambarMateriActivity
                Intent intent = new Intent(EditActivity.this, PilihGambarMateriActivity.class);
                startActivity(intent);
            }
        });
    }
}

