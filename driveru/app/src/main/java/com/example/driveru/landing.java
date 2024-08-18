package com.example.driveru;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class landing extends AppCompatActivity {
    public Button add1,rem1,rfresh;

    public TextView tv,cvname,dnummmm,dnames;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        add1 = findViewById(R.id.put1);
        rem1 = findViewById(R.id.remove1);
        rfresh = findViewById(R.id.refresh);
        add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(landing.this,diverallocate.class);
                startActivity(i);

            }
        });
        rem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkHttpClient okhttpclient = new OkHttpClient();
                String url = "http://192.168.43.111:8000/drop";
                Request request = new Request.Builder().url(url).build();
                okhttpclient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        Toast.makeText(landing.this, "Server Error", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String msg = response.body().string();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(landing.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                });


            }
        });
        rfresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkHttpClient okhttpclient = new OkHttpClient();
                String url = "http://192.168.43.111:8000/getdata";
                Request request = new Request.Builder().url(url).build();
                okhttpclient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        Toast.makeText(landing.this, "Server Error", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String res = response.body().string();
                        String[] amp = res.split(",");
                        cvname = findViewById(R.id.carname);
                        cvname.setText(amp[0]);
                        dnummmm = findViewById(R.id.dnumm);
                        dnummmm.setText(amp[1]);
                        dnames = findViewById(R.id.dnames);
                        dnames.setText(amp[2]);


                    }
                });
            }
        });


    }
}