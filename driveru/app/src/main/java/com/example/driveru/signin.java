package com.example.driveru;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class signin extends AppCompatActivity {
    public EditText mobile,pass;
    private CheckBox cb;
    Button signin,signup,next;
    private TextView td;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        signin = findViewById(R.id.signin);
        signup = findViewById(R.id.signup);
        next =  findViewById(R.id.hj);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(signin.this,landing.class);
                startActivity(i);

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(signin.this,MainActivity.class);
                startActivity(i);
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cb = findViewById(R.id.checkBox);
                if (cb.isChecked()){
                    mobile = findViewById(R.id.mobile);
                    pass = findViewById(R.id.pass);
                    OkHttpClient okhttpclient = new OkHttpClient();

                    String url = "http://192.168.32.194:8000/validate/"+mobile.getText().toString();
                    Request request = new Request.Builder().url(url).build();
                    okhttpclient.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {
                            Toast.makeText(signin.this, "Server Error", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            String valpas = response.body().string();
                            pass.setText(valpas);
                        }
                    });
                }
                else{
                    Toast.makeText(signin.this, "Password not validated", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}