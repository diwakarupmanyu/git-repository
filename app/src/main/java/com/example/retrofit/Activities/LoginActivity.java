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

import com.example.retrofit.ModelResponse.LoginResponse;
import com.example.retrofit.ModelResponse.RegisterResponse;
import com.example.retrofit.ModelResponse.User;
import com.example.retrofit.R;
import com.example.retrofit.RetrofitClient;
import com.example.retrofit.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    TextView registerlink;
    Button btnlogin;
    EditText email,password;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //hide ActionBar
        getSupportActionBar().hide();

        //hide statusBar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        registerlink = findViewById(R.id.registerlink);
        email = findViewById(R.id.email);
        password= findViewById(R.id.password);
        btnlogin= findViewById(R.id.btnlogin);

        btnlogin.setOnClickListener(this);
        registerlink.setOnClickListener(this);

        sharedPrefManager= new SharedPrefManager(getApplicationContext());

    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnlogin:
                userLogin();
                break;

            case R.id.registerlink:
                switchOnRegister();
                break;
        }

    }

    private void userLogin()
    {
        String userEmail = email.getText().toString();
        String userPassword = password.getText().toString();

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

        Call<LoginResponse> call= RetrofitClient.getInstance().getApi().login(userEmail,userPassword);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                LoginResponse loginResponse = response.body();
                if(response.isSuccessful())
                {
                    if(loginResponse.getError().equals("200"))
                    {
                        sharedPrefManager.saveUser(loginResponse.getUser());
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        Toast.makeText(LoginActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });


    }

    private void switchOnRegister()
    {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(sharedPrefManager.isLoggedIn())
        {
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}