package com.example.lt_day12;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Runnable {
    private Button btnStart;
    private EditText edtTime;
    private TextView tvTime;
    private int time;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            int what = msg.what;
            if (what==1){
                tvTime.setText(time + "");
            }else if (what ==2){
                int value = msg.arg2;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStart = findViewById(R.id.btn_start);
        edtTime = findViewById(R.id.edt_time);
        tvTime = findViewById(R.id.tv_time);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time = Integer.parseInt(edtTime.getText().toString());
                Thread thread = new Thread(MainActivity.this);
                thread.start();
            }
        });
    }

    @Override
    public void run() {
        while (time > 0) {
            time--;
            handler.sendEmptyMessage(1);
            Message message = new Message();
            message.what=2;
            message.arg2 =1000;

            handler.sendMessage(message);
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    tvTime.setText(time+"");
//                }
//            });

//            tvTime.post(new Runnable() {
//                @Override
//                public void run() {
//                    tvTime.setText(time +"");
//                }
//            });
//            tvTime.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    tvTime.setText(time+"");
//                }
//            },2000);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void dowload(View view) {
        Intent intent = new Intent(MainActivity.this,DowloadAcitivy.class);
        startActivity(intent);
    }
}