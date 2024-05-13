package com.example.kioskita;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView tv3 = findViewById(R.id.textView3);
        String kelas = getIntent().getStringExtra("kelas");
        tv3.setText(kelas);

    }
}