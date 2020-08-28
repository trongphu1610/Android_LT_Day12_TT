package com.example.lt_day12;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class DowloadAcitivy extends AppCompatActivity {
    private ImageView imgAvatar;
    private Button btnDowload;
    private EditText edtLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dowload_acitivy);
        imgAvatar = findViewById(R.id.img_avatar);
        edtLink = findViewById(R.id.edt_link);
        btnDowload = findViewById(R.id.btn_dowload);
        btnDowload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String link = edtLink.getText().toString();
                new DownloadTask().execute(link);
            }
        });
    }

    class DownloadTask extends AsyncTask<String, Void, Bitmap>// 3 tham so , tham so thứ nhất là kiểu truyền vào tham số thứ 2 là có update không và kiểu trả về còn thứ 3 là kiểu dữ liệu trả về
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(DowloadAcitivy.this,"Starting",Toast.LENGTH_SHORT).show();

        }

        @Override
        protected Bitmap doInBackground(String... args) {// khi có dấu chấm thì là kiểu mảng
            String link = args[0];
            try {
                URL url = new URL(link);
                URLConnection urlConnection = url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                return BitmapFactory.decodeStream(inputStream);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imgAvatar.setImageBitmap(bitmap);
            Toast.makeText(DowloadAcitivy.this,"done",Toast.LENGTH_SHORT).show();
        }
    }
}