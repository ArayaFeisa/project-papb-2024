package com.example.kioskita;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MateriBaruActivity extends AppCompatActivity {

    MateriBaruFragment materiBaruFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materi_baru);

        materiBaruFragment = new MateriBaruFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.flContainer, materiBaruFragment, "main")
                .addToBackStack(null)
                .commit();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void backToMain(View view) {
        finish();
    }
}