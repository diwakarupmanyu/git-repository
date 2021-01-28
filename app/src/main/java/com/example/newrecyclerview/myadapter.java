package com.example.newrecyclerview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myadapter extends RecyclerView.Adapter<myviewholder>
{
    ArrayList<Model> data;
    Context context;
    public myadapter(ArrayList<Model> data, Context context)
    {
        this.data = data;
        this.context=context;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.singlerow,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position)
    {
        final Model temp = data.get(position);

        holder.t1.setText(data.get(position).getRollno());
        holder.t2.setText(data.get(position).getName());
        holder.t3.setText(data.get(position).getDegree());

        holder.t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,detailActivity.class);
                intent.putExtra("rollno",temp.getRollno());
                intent.putExtra("name",temp.getName());
                intent.putExtra("degree",temp.getDegree());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
