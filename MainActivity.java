package com.example.kioskitalocal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView kelasPic;
    private TextView judultext;
    private TextView lihatSemua;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.kelasPic = this.findViewById(R.id.kelasPic);
        this.kelasPic.setOnClickListener(this);

        this.lihatSemua = this.findViewById(R.id.tv_lihatSemua);
        this.lihatSemua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MoreTaskActivity.class);
                startActivity(i);
            }
        });

        judultext = findViewById(R.id.judul);

        //        kelasPic.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
//                startActivity(intent);
//            }
//        });

        Intent in = getIntent();
        if (in.hasExtra("judul")){
            String judul = in.getStringExtra("judul");
            judultext.setText(judul);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.kelasPic) {
            Intent i = new Intent(this, EditActivity.class);
            startActivity(i);
        }
    }
}