package com.example.driveru;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private EditText name,mobile,gmail,pass,error;
    private CheckBox cb;

    Button signup,signin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signup = findViewById(R.id.signin);
        signin = findViewById(R.id.signup);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,signin.class);
                startActivity(i);
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = findViewById(R.id.name);
                mobile = findViewById(R.id.mobile);
                gmail = findViewById(R.id.gmail);
                pass = findViewById(R.id.pass);
                cb = findViewById(R.id.checkBox);
                error = findViewById(R.id.error);
                if (cb.isChecked()&& name.length() !=0 && mobile.length()==10 && gmail.length()!=0 &&pass.length()!=0){

                    OkHttpClient okhttpclient = new OkHttpClient();
                    RequestBody reqbody = new FormBody.Builder().add("name",name.getText().toString()).add("mobile",mobile.getText().toString()).add("gmail",gmail.getText().toString()).add("pass",pass.getText().toString()).build();
                    String url = "http://192.168.43.111:8000/data";
                    Request request = new Request.Builder().url(url).post(reqbody).build();
                    okhttpclient.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {
                            Toast.makeText(MainActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            String res =  response.body().string();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this, "Signup Successful", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(MainActivity.this,signin.class);
                                    startActivity(i);
                                }
                            });


                        }

                    });




                }
                else{
                    if(name.length() ==0){
                        error.setText("Check the name field");
                        Toast.makeText(MainActivity.this, "Check the name field", Toast.LENGTH_SHORT).show();
                    }
                    if(mobile.length()!=10){
                        error.setText("Check the mobile number  field");
                        Toast.makeText(MainActivity.this, "Check the mobile number  field", Toast.LENGTH_SHORT).show();
                    }
                    if(gmail.length() ==0){
                        error.setText("Check gmail field");
                        Toast.makeText(MainActivity.this, "Check the gmail field", Toast.LENGTH_SHORT).show();
                    }
                    if(pass.length()==0){
                        error.setText("Check the password  field");
                        Toast.makeText(MainActivity.this, "Check the Password number  field", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });





    }
}