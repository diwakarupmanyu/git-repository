package com.example.recyclerrview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    TextView tv1,tv2;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Details");
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

         tv1=(TextView)findViewById(R.id.desc_name);
         tv2=(TextView)findViewById(R.id.desc_branch);
         image=(ImageView)findViewById(R.id.image);


        tv1.setText(getIntent().getStringExtra("name"));
        tv2.setText(getIntent().getStringExtra("branch"));
        image.setImageResource(getIntent().getIntExtra("image",0));
    }
}