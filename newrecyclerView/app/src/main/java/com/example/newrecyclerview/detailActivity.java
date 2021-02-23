package com.example.newrecyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class detailActivity extends AppCompatActivity
{
   TextView tv1,tv2,tv3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tv1=(TextView)findViewById(R.id.desc_rollno);
        tv2=(TextView)findViewById(R.id.desc_name);
        tv3=(TextView)findViewById(R.id.desc_degree);

        tv1.setText(getIntent().getStringExtra("rollno"));
        tv2.setText(getIntent().getStringExtra("name"));
        tv3.setText(getIntent().getStringExtra("degree"));
    }
}