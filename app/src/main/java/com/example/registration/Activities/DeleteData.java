package com.example.registration.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.registration.DBHelperClasses.DatabaseHelper;
import com.example.registration.R;

public class DeleteData extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    EditText deleteId;
    Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_data);

        deleteId = findViewById(R.id.deleteId);
        delete = findViewById(R.id.delete);

        databaseHelper = new DatabaseHelper(this);

    }

    public void delete(View view) {
        Integer result = databaseHelper.deleteData(deleteId.getText().toString());
        if(result>0)
        {
            Toast.makeText(getApplicationContext(),"Data deleted",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(),"Data not deleted",Toast.LENGTH_SHORT).show();
        }

    }
}