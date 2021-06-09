package com.example.registration.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.GridView;

import com.example.registration.AdapterClass.DataFetchAdapter;
import com.example.registration.DBHelperClasses.DatabaseHelper;
import com.example.registration.ModelClass.UserDataResponse;
import com.example.registration.R;

import java.util.ArrayList;

public class ShowData extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    GridView gridView;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        gridView = findViewById(R.id.dataList);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        databaseHelper = new DatabaseHelper(this);

        ArrayList<UserDataResponse> dataArrayList = databaseHelper.showData();

        DataFetchAdapter adapter = new DataFetchAdapter(dataArrayList);
        gridView.setAdapter(adapter);
        progressDialog.dismiss();

    }
}