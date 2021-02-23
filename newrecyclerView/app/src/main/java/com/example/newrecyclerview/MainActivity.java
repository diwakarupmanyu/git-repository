package com.example.newrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    RecyclerView rcv;
    myadapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("RecyclerView");

        rcv = (RecyclerView)findViewById(R.id.recview);
        rcv.setLayoutManager(new LinearLayoutManager(this));

        adapter = new myadapter(dataqueue(),getApplicationContext());
        rcv.setAdapter(adapter);

    }
    public ArrayList<Model> dataqueue()
    {
        ArrayList<Model> holder = new ArrayList<>();

        Model ob1 = new Model();
        ob1.setRollno("1001");
        ob1.setName("Amit");
        ob1.setDegree("BE");
        holder.add(ob1);

        Model ob2 = new Model();
        ob2.setRollno("1002");
        ob2.setName("Deepak");
        ob2.setDegree("BCA");
        holder.add(ob2);

        Model ob3 = new Model();
        ob3.setRollno("1003");
        ob3.setName("Manan");
        ob3.setDegree("MCA");
        holder.add(ob3);

        Model ob4 = new Model();
        ob4.setRollno("1004");
        ob4.setName("Rahul");
        ob4.setDegree("BBA");
        holder.add(ob4);

        Model ob5 = new Model();
        ob5.setRollno("1005");
        ob5.setName("Harry");
        ob5.setDegree("ME");
        holder.add(ob5);

        return holder;
    }

    public static class detailActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_detail);
        }
    }
}