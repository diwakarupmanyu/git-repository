package com.example.registration.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.registration.DBHelperClasses.DatabaseHelper;
import com.example.registration.ModelClass.UserDataResponse;
import com.example.registration.R;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    EditText name,city,age,phone,gmail,password;
    Button register,showData,updateData,deleteData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        databaseHelper = new DatabaseHelper(this);


        register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    userInsertData();
                }
            });

        showData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(MainActivity.this,ShowData.class));
               finish();
            }
        });

        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,UpdateData.class));
            }
        });

        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,DeleteData.class));
            }
        });
    }


    private void userInsertData() {
        String userName = name.getText().toString();
        String userAge = age.getText().toString();
        String userCity = city.getText().toString();
        String userPhone = phone.getText().toString();
        String userGmail = gmail.getText().toString();
        String userPass = password.getText().toString();

        if(userName.isEmpty())
        {
            name.requestFocus();
            name.setError("Please enter name!");
            return;
        }
        if(userPhone.isEmpty())
        {
            phone.requestFocus();
            phone.setError("enter your phone no.");
            return;
        }
        if(userPhone.length()!=10)
        {
            phone.requestFocus();
            phone.setError("Please enter correct no.");
            return;
        }
        if(userGmail.isEmpty())
        {
            gmail.requestFocus();
            gmail.setError("Please enter your email");
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(userGmail).matches())
        {
            gmail.requestFocus();
            gmail.setError("Enter your Correct email");
            return;
        }
        if(userPass.isEmpty())
        {
            password.requestFocus();
            password.setError("Please enter your password");
            return;
        }
        if(userPass.length()<8)
        {
            password.requestFocus();
            password.setError("mininum 8 character password");
            return;
        }

        UserDataResponse dataResponse = new UserDataResponse();

        if (dataResponse != null) {
            dataResponse.setName(userName);
            dataResponse.setAge(userAge);
            dataResponse.setCity(userCity);
            dataResponse.setPhone(userPhone);
            dataResponse.setGmail(userGmail);
            dataResponse.setPassword(userPass);
        }

        boolean chk = databaseHelper.insertData(dataResponse);

        if (chk == true) {
            Toast.makeText(getApplicationContext(), "save successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_SHORT).show();
        }

    }


    private void init() {

        name = findViewById(R.id.name);
        city = findViewById(R.id.city);
        age = findViewById(R.id.age);
        phone = findViewById(R.id.phone);
        gmail = findViewById(R.id.gmailAddress);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);
        showData = findViewById(R.id.showData);
        updateData = findViewById(R.id.updateData);
        deleteData = findViewById(R.id.deleteData);
    }

}