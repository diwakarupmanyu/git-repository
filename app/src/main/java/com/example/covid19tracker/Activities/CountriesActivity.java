package com.example.covid19tracker.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.example.covid19tracker.AdapterClass.CountryAdapter;
import com.example.covid19tracker.ModelClass.CountryData;
import com.example.covid19tracker.R;
import com.example.covid19tracker.Services.ApiInterface;
import com.example.covid19tracker.Services.ApiUtilities;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountriesActivity extends AppCompatActivity {
   private RecyclerView recyclerView;
   private ProgressDialog progressDialog;
   private EditText searchBar;
   private CountryAdapter adapter;
   List<CountryData> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries);

        recyclerView = findViewById(R.id.countries);
        searchBar = findViewById(R.id.searchBar);

        list = new ArrayList<>();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CountriesActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new CountryAdapter(list,this);
        recyclerView.setAdapter(adapter);


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        ApiInterface apiInterface = ApiUtilities.getApiInterface().create(ApiInterface.class);
        apiInterface.getCountryData()
                .enqueue(new Callback<List<CountryData>>() {
                    @Override
                    public void onResponse(Call<List<CountryData>> call, Response<List<CountryData>> response) {

                        if(response!=null) {
                           list.addAll(response.body());
                           adapter.notifyDataSetChanged();
                        }
                        progressDialog.dismiss();

                    }

                    @Override
                    public void onFailure(Call<List<CountryData>> call, Throwable t) {
                        Toast.makeText(CountriesActivity.this,"Please try Again...",Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });

        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());

            }
        });

    }

    private void filter(String text) {
        List<CountryData> filterList = new ArrayList<>();

        for(CountryData items : list )
        {
           if(items.getCountry().toLowerCase().contains(text.toLowerCase()))
           {
               filterList.add(items);
           }
        }
        adapter.filterList(filterList);
    }



    /*
    private void generateCountryList(List<CountryData> data)
    {
         adapter = new CountryAdapter(data,this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CountriesActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    } */
}