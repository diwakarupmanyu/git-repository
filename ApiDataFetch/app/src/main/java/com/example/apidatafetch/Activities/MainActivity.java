package com.example.apidatafetch.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.example.apidatafetch.AdapterClasses.RecyclerAdapter;
import com.example.apidatafetch.Services.CallApiInterface;
import com.example.apidatafetch.Services.FetchDataApi;
import com.example.apidatafetch.ModelClasses.FetchResponse;
import com.example.apidatafetch.R;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity  {

    RecyclerView recyclerView;
    ProgressDialog progressDialog;
    //TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       recyclerView = findViewById(R.id.recyclerview);
       progressDialog = new ProgressDialog(MainActivity.this);
       progressDialog.setMessage("Loading...");
       progressDialog.setCancelable(false);
       progressDialog.show();


      //  textView=findViewById(R.id.showdata);
       // textView.setText("");
        CallApiInterface callApiInterface =FetchDataApi.getFetchData().create(CallApiInterface.class);
        Call<List<FetchResponse>> listdata= callApiInterface.getData();
        listdata.enqueue(new Callback<List<FetchResponse>>() {
            @Override
            public void onResponse(Call<List<FetchResponse>> call, Response<List<FetchResponse>> response) {
                progressDialog.dismiss();
                if(response!=null) {
                    Generatelistdata(response.body());
                }
               /* for(int i=0;i<data.size();i++)
                   textView.append("Id : "+data.get(i).getId()+"\nTitle: "+data.get(i).getTitle()+"\n\n");*/
            }

            @Override
            public void onFailure(Call<List<FetchResponse>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this,"Something went wrong...Please try later!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Generatelistdata(List<FetchResponse> data)
    {
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(data, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
       // LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerAdapter);

    }

}