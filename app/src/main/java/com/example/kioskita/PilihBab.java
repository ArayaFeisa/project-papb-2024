package com.example.kioskita;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class PilihBab extends AppCompatActivity {

    private List<JudulBab> data;
    private RecyclerView rvSub;
    ImageView ivBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_sub);

        Handler h = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                Bundle bundle = msg.getData();
                String hasil = bundle.getString("hasil");
                Bitmap image = bundle.getParcelable("image");

                rvSub = findViewById(R.id.rvSub);
                ivBackground = findViewById(R.id.ivBackground);
                ivBackground.setImageBitmap(image);

                Gson gson = new Gson();
                JudulBab[] babs = gson.fromJson(hasil, JudulBab[].class);
                int[] images = {R.drawable.organisasimage, R.drawable.keuangan, R.drawable.bisnis, R.drawable.analisis,
                        R.drawable.penjualan, R.drawable.industri, R.drawable.teknologi, R.drawable.risiko};

                data = new ArrayList<JudulBab>();

                for (int i = 0; i < babs.length; i++) {
                    data.add(babs[i]);
                    babs[i].ilustrasi = images[i];
                }

                BabAdapter babAdapter = new BabAdapter(PilihBab.this, data);

                RecyclerView.LayoutManager lm = new LinearLayoutManager(PilihBab.this);
                rvSub.setLayoutManager(lm);
                rvSub.setAdapter(babAdapter);

            }
        };
        Thread t = new BabThread(h);
        t.start();

//        this.data = new ArrayList<JudulBab>();
//
//        JudulBab bab1 = new JudulBab(R.drawable.organisasi, "Organisasi", "Bagaimana etika dan tips bergorganisasi");
//        JudulBab bab2= new JudulBab(R.drawable.keuangan, "Finansial", "Pengaturan keuangan dan pengelolaan anggaran");
//        JudulBab bab3 = new JudulBab(R.drawable.bisnis, "Etika", "Prinsip-prinsip moral dalam operasi bisnis");
//        JudulBab bab4 = new JudulBab(R.drawable.analisis, "Analisis", "Menyelidiki permintaan pasar dan tren konsumen");
//        JudulBab bab5 = new JudulBab(R.drawable.penjualan, "Strategi", "Langkah untuk memasarkan dan menjual produk");
//        JudulBab bab6 = new JudulBab(R.drawable.industri, "Persaingan", "Memahami pesaing dan dinamika dalam industri");
//        JudulBab bab7 = new JudulBab(R.drawable.teknologi, "Teknologi", "Pemanfaatan teknologi untuk operasi bisnis");
//        JudulBab bab8 = new JudulBab(R.drawable.risiko, "Manajemen", " Identifikasi dan penanganan risiko dalam bisnis");
//
//        this.data.add(bab1);
//        this.data.add(bab2);
//        this.data.add(bab3);
//        this.data.add(bab4);
//        this.data.add(bab5);
//        this.data.add(bab6);
//        this.data.add(bab7);
//        this.data.add(bab8);



//        BabAdapter babAdapter = new BabAdapter(PilihBab.this, this.data);
//
//        RecyclerView.LayoutManager lm = new LinearLayoutManager(PilihBab.this);
//        this.rvSub.setLayoutManager(lm);
//        this.rvSub.setAdapter(babAdapter);

    }

    public void back(View view) {
        super.onBackPressed();
    }

    public void terpilih(View view) {
        Button bt = (Button) view;
        Toast.makeText(PilihBab.this,
                "Judul Bab: " + bt.getTag().toString(),
                Toast.LENGTH_SHORT).show();
        Intent retuntIntent = new Intent();
        retuntIntent.putExtra("bab", bt.getTag().toString());
        this.setResult(RESULT_OK, retuntIntent);

        finish();
    }
}
