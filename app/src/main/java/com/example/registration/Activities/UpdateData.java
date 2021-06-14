package com.example.registration.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.registration.DBHelperClasses.DatabaseHelper;
import com.example.registration.ModelClass.UserDataResponse;
import com.example.registration.R;

import java.util.ArrayList;

public class UpdateData extends AppCompatActivity {
    UserDataResponse dataResponse;
    DatabaseHelper databaseHelper;
    EditText updateId,updateName,updateAge,updateCity,updatePhone,updateGmail,updatePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

        init();

        databaseHelper = new DatabaseHelper(this);


    }


    public void update(View view) {

        dataResponse = new UserDataResponse();

       // String Id = updateId.getText().toString();
        String Name = updateName.getText().toString();
        String Age = updateAge.getText().toString();
        String City = updateCity.getText().toString();
        String Phone = updatePhone.getText().toString();
        String Gmail = updateGmail.getText().toString();
        String Pass = updatePassword.getText().toString();


       /* if(Id.isEmpty())
        {
            updateId.requestFocus();
            updateId.setError("Please enter id");
            return;
        } */
        if(Name.isEmpty())
        {
            updateName.requestFocus();
            updateName.setError("Please enter name!");
            return;
        }
        if(Age.isEmpty())
        {
            updateAge.requestFocus();
            updateAge.setError("Please enter age");
            return;
        }
        if(City.isEmpty())
        {
            updateCity.requestFocus();
            updateCity.setError("Please enter city");
            return;
        }

        if(Phone.isEmpty())
        {
            updatePhone.requestFocus();
            updatePhone.setError("enter your phone no.");
            return;
        }
        if(Phone.length()!=10)
        {
            updatePhone.requestFocus();
            updatePhone.setError("Please enter correct no.");
            return;
        }
        if(Gmail.isEmpty())
        {
            updateGmail.requestFocus();
            updateGmail.setError("Please enter your email");
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(Gmail).matches())
        {
            updateGmail.requestFocus();
            updateGmail.setError("Enter your Correct email");
            return;
        }
        if(Pass.isEmpty())
        {
            updatePassword.requestFocus();
            updatePassword.setError("Please enter your password");
            return;
        }
        if(Pass.length()<8)
        {
            updatePassword.requestFocus();
            updatePassword.setError("mininum 8 character password");
            return;
        }


       if (dataResponse != null) {
            dataResponse.setId(dataResponse.getId());
            dataResponse.setName(Name);
            dataResponse.setAge(Age);
            dataResponse.setCity(City);
            dataResponse.setPhone(Phone);
            dataResponse.setGmail(Gmail);
            dataResponse.setPassword(Pass);
        }
        boolean chk = databaseHelper.updateData(dataResponse);

        if (chk == true) {
            Toast.makeText(getApplicationContext(), "Update successfully", Toast.LENGTH_SHORT).show();
            finish();

        } else {
            Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_SHORT).show();
        }

    }

    private void init() {
       // updateId = findViewById(R.id.updateId);
        updateName = findViewById(R.id.updateName);
        updateAge = findViewById(R.id.updateAge);
        updateCity = findViewById(R.id.updateCity);
        updatePhone = findViewById(R.id.updatePhone);
        updateGmail = findViewById(R.id.updateGmailAddress);
        updatePassword = findViewById(R.id.updatePassword);
    }

}