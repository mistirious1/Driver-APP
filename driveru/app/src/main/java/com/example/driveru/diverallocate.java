package com.example.driveru;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class diverallocate extends AppCompatActivity {
    public EditText carname,modelname,lplate,pincode;
    public Button search;
    public TextView opt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diverallocate);
        search = findViewById(R.id.go);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carname = findViewById(R.id.car);
                modelname =  findViewById(R.id.model);
                lplate = findViewById(R.id.plate);
                pincode = findViewById(R.id.pin);
                opt = findViewById(R.id.output);
                OkHttpClient okhttpclient = new OkHttpClient();
                RequestBody reqbody = new FormBody.Builder().add("carname",carname.getText().toString()).add("model",modelname.getText().toString()).add("lplate",lplate.getText().toString()).add("pincode",pincode.getText().toString()).build();
                String url = "http://192.168.43.111:8000/addcar";
                Request request = new Request.Builder().url(url).post(reqbody).build();
                okhttpclient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        opt.setText("Error getting network");
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String res =  response.body().string();

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(diverallocate.this, res, Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(diverallocate.this,landing.class);
                                startActivity(i);
                            }
                        });


                    }

                });
            }
        });
    }
}