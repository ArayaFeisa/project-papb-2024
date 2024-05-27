package com.example.kioskita;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HomeActivity extends AppCompatActivity {

    MateriDatabase materiDatabase;
    List<MateriModel> materiList;
    TextView tvListmateri;
    Button btLogout;
    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tvListmateri = findViewById(R.id.tvListMateri);
        btLogout = findViewById(R.id.btLogout);

        mAuth = FirebaseAuth.getInstance();

        btLogout.setOnClickListener(view -> {
            logOut();
        });

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
    }

    public void logOut() {
        mAuth.signOut();

        Intent intent = new Intent(HomeActivity.this,
                LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_CLEAR_TASK);//makesure user cant go back
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        getMateriBackground();

    }

    public void lihatMateri(View view) {
        Intent i = new Intent(this, MateriBaruActivity.class);
        startActivity(i);
    }

    public void getMateriBackground() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executorService.execute(new Runnable() {
            @Override
            public void run() {

                int jumlahMateri = materiDatabase.getMateriDAO().countMateri();

                if (jumlahMateri > 0) {
                    materiList = materiDatabase.getMateriDAO().getAllMateri();


                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            StringBuilder sb = new StringBuilder();
                            for (MateriModel m : materiList) {
                                sb.append("Judul: " + m.getJudul() + "\nBab: " + m.getBab() + "\nDeskripsi: " + m.getDeskripsi());
                                sb.append("\n\n");
                            }
                            String finalData = sb.toString();
                            tvListmateri.setText(finalData);

//                        Toast.makeText(MainActivity.this, ""+finalData, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}