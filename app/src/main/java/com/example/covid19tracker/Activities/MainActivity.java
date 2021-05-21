package com.example.covid19tracker.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.covid19tracker.ModelClass.CountryData;
import com.example.covid19tracker.R;
import com.example.covid19tracker.Services.ApiInterface;
import com.example.covid19tracker.Services.ApiUtilities;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class MainActivity extends AppCompatActivity  {

  private  TextView totalConfirm,totalActive,totalTests,totalRecovered,totalDeaths;
  private  TextView todayConfirm,todayRecovered,todayDeaths;
  private  PieChart pieChart;
  private  TextView date,cName;
  private  List<CountryData> list;
  private ProgressDialog progressDialog;

  String country = "India";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

          init();

          progressDialog = new ProgressDialog(this);
          progressDialog.setMessage("Loading...");
          progressDialog.setCancelable(false);
          progressDialog.show();

          list = new ArrayList<>();

        if(getIntent().getStringExtra("country") != null)
            country = getIntent().getStringExtra("country");

         cName.setText(country);  

        cName.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this,CountriesActivity.class)));



       ApiInterface apiInterface =ApiUtilities.getApiInterface().create(ApiInterface.class);
       apiInterface.getCountryData()
                .enqueue(new Callback<List<CountryData>>() {
                    @Override
                    public void onResponse(Call<List<CountryData>> call, Response<List<CountryData>> response)
                    {
                                if(response!=null) {
                                 list.addAll(response.body());
                                 }
                               progressDialog.dismiss();

                        for ( int i=0; i<list.size(); i++)
                        {
                            if(list.get(i).getCountry().equals(country))
                            {
                                int confirm = Integer.parseInt(list.get(i).getCases());
                                int active = Integer.parseInt(list.get(i).getActive());
                                int recovered = Integer.parseInt(list.get(i).getRecovered());
                                int death = Integer.parseInt(list.get(i).getDeaths());

                                totalConfirm.setText(NumberFormat.getInstance().format(confirm));
                                totalActive.setText(NumberFormat.getInstance().format(active));
                                totalRecovered.setText(NumberFormat.getInstance().format(recovered));
                                totalDeaths.setText(NumberFormat.getInstance().format(death));

                                todayConfirm.setText(NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getTodayCases())));
                                todayRecovered.setText(NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getTodayRecovered())));
                                todayDeaths.setText(NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getTodayDeaths())));
                                totalTests.setText(NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getTests())));

                                pieChart.addPieSlice(new PieModel("yellow",confirm,getResources().getColor(R.color.yellow)));
                                pieChart.addPieSlice(new PieModel("blue_pie",active,getResources().getColor(R.color.blue_pie)));
                                pieChart.addPieSlice(new PieModel("green_pie",recovered,getResources().getColor(R.color.green_pie)));
                                pieChart.addPieSlice(new PieModel("red_pie",death,getResources().getColor(R.color.red_pie)));
                                pieChart.startAnimation();

                                setText(list.get(i).getUpdated());

                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<List<CountryData>> call, Throwable t) {

                        Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_SHORT).show();
                             progressDialog.dismiss();
                    }
                });
    }


    private void init() {
        totalConfirm = findViewById(R.id.totalConfirm);
        totalActive = findViewById(R.id.totalActive);
        totalTests = findViewById(R.id.totalTests);
        totalRecovered = findViewById(R.id.totalRecovered);
        totalDeaths = findViewById(R.id.totalDeath);
        todayConfirm = findViewById(R.id.todayConfirm);
        todayRecovered = findViewById(R.id.todayRecovered);
        todayDeaths = findViewById(R.id.todayDeath);
        pieChart = findViewById(R.id.piechart);
        date =  findViewById(R.id.date);
        cName = findViewById(R.id.cname);
    }

    private void setText(String updated) {
        DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
        long milliseconds = Long.parseLong(updated);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);

        date.setText("Updated at "+ dateFormat.format(calendar.getTime()));

    }

}
