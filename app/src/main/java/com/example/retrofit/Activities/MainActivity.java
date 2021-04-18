package com.example.retrofit.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofit.ModelResponse.RegisterResponse;
import com.example.retrofit.R;
import com.example.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView loginlink;
    EditText name,email,password;
    Button btnregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //hide ActionBar
        getSupportActionBar().hide();

        //hide statusBar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        name = findViewById(R.id.name);
        email= findViewById(R.id.email);
        password=findViewById(R.id.password);
        btnregister = findViewById(R.id.btnregister);
        loginlink = findViewById(R.id.loginlink);

        btnregister.setOnClickListener(this);
        loginlink.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btnregister:
                registerUser();
                break;

            case R.id.loginlink:
                switchOnLogin();
                break;
        }

    }

    private void registerUser()
    {
        String userName = name.getText().toString();
        String userEmail = email.getText().toString();
        String userPassword = password.getText().toString();

        if(userName.isEmpty())
        {
            name.requestFocus();
            name.setError("Please enter your name");
            return;
        }

        if(userEmail.isEmpty())
        {
         email.requestFocus();
         email.setError("Please enter your email");
         return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches())
        {
            email.requestFocus();
            email.setError("Please enter correct email");
            return;
        }
        if(userPassword.isEmpty())
        {
            password.requestFocus();
            password.setError("Please enter your password");
            return;
        }
        if(userPassword.length()<8)
        {
            password.requestFocus();
            password.setError("Password length is 8");
            return;
        }

        Call<RegisterResponse> call= RetrofitClient
                .getInstance()
                .getApi()
                .register(userName,userEmail,userPassword);

        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
               RegisterResponse registerResponse = response.body();
               if(response.isSuccessful()) {
                   Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                   intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                   startActivity(intent);
                   finish();
                   Toast.makeText(MainActivity.this, registerResponse.getMessage(), Toast.LENGTH_SHORT).show();
               }
               else
               {
                   Toast.makeText(MainActivity.this, registerResponse.getMessage() ,Toast.LENGTH_SHORT).show();
               }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
               Toast.makeText(MainActivity.this, t.getMessage() ,Toast.LENGTH_SHORT).show();


            }
        });

    }

    private void switchOnLogin()
    {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}