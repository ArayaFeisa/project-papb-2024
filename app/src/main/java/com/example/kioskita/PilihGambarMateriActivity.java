package com.example.kioskitalocal;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class PilihGambarMateriActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<String> imageUrlList;
    private GambarAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilihgambarmateri);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        imageUrlList = new ArrayList<>();
        adapter = new GambarAdapter(imageUrlList);
        recyclerView.setAdapter(adapter);

        new FetchImageData().execute("https://yoruban-witness.000webhostapp.com/");
    }

    private class FetchImageData extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... urls) {
            String urlString = urls[0];
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(urlString);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuilder buffer = new StringBuilder();
                if (inputStream == null) {
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line).append("\n");
                }

                if (buffer.length() == 0) {
                    return null;
                }

                String jsonData = buffer.toString();
                parseImageData(jsonData);

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            adapter.notifyDataSetChanged();
        }
    }

    private void parseImageData(String jsonData) {
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<ImageData>>() {}.getType();
        ArrayList<ImageData> imageDataList = gson.fromJson(jsonData, listType);

        for (ImageData imageData : imageDataList) {
            imageUrlList.add(imageData.getImageUrl());
        }
    }
}
