package com.example.kioskita;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class BabThread extends Thread {

    private final Handler h;

    BabThread(Handler h) {
        this.h = h;
    }

    @Override
    public void run() {
        super.run();
        try {
            URL url = new URL("https://haldaiva.000webhostapp.com/request-bab.php");
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("User-Agent", "Bab");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
//
//            String dataToSend = "";
//
//            OutputStream os = conn.getOutputStream();
//            os.write(dataToSend.getBytes());
//            os.flush();
//            os.close();


            if (conn.getResponseCode() == 200) {
                InputStream is = conn.getInputStream();

                StringBuffer sb = new StringBuffer();
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(is)
                );
                String line;
                while ((line = br.readLine()) != null)
                    sb.append(line);
                br.close();
                String hasilJson = sb.toString();

                Bitmap image = downloadImage("https://haldaiva.000webhostapp.com/homeImageLGreen.jpg");

                Message message = h.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putString("hasil", hasilJson);
                if (image != null) {
                    bundle.putParcelable("image", image);
                }
                message.setData(bundle);
                h.sendMessage(message);

            } else {

            }

        } catch (Exception e) {

        }
    }

    private Bitmap downloadImage(String urlString) {
        HttpsURLConnection connection;
        InputStream inputStream;
        try {
            URL url = new URL(urlString);
            connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            // Check if the request succeeded
//            int responseCode = connection.getResponseCode();
//            if (responseCode != HttpsURLConnection.HTTP_OK) {
//                throw new Exception("HTTP error code: " + responseCode);
//            }

            inputStream = connection.getInputStream();
            return BitmapFactory.decodeStream(inputStream);

        } catch (Exception e) {
//            e.printStackTrace();
            return null;
        } /*finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                connection.disconnect();
            }
        }*/
    }
}
