package com.example.apidatafetch.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apidatafetch.AdapterClasses.DataAdapter;
import com.example.apidatafetch.Api.CallApiInterface;
import com.example.apidatafetch.FetchDataApi;
import com.example.apidatafetch.ModelClasses.FetchResponse;
import com.example.apidatafetch.R;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

   // RecyclerView recyclerView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      //  recyclerView = findViewById(R.id.recyclerview);
        textView=findViewById(R.id.showdata);
        textView.setText("");

        listingdata();

      //  LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        //recyclerView.setLayoutManager(linearLayoutManager);


    }

    private void listingdata()
    {
        CallApiInterface callApiInterface =FetchDataApi.getFetchData().create(CallApiInterface.class);
        Call<List<FetchResponse>> listingdata= callApiInterface.getData();
        listingdata.enqueue(new Callback<List<FetchResponse>>() {
            @Override
            public void onResponse(Call<List<FetchResponse>> call, Response<List<FetchResponse>> response) {
                List<FetchResponse> data = response.body();
                for(int i=0;i<data.size();i++)
                    textView.append("Id : "+data.get(i).getId()+"\nTitle: "+data.get(i).getTitle()+"\n\n");

            }

            @Override
            public void onFailure(Call<List<FetchResponse>> call, Throwable t) {

                Toast.makeText(getApplicationContext(),"Failure",Toast.LENGTH_SHORT).show();

            }
        });
    }
}