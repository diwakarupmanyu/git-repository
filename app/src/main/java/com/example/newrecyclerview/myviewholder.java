package com.example.newrecyclerview;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class myviewholder extends RecyclerView.ViewHolder
{
    TextView t1,t2,t3;
    public myviewholder(@NonNull View itemView) {
        super(itemView);
        t1=(TextView)itemView.findViewById(R.id.t1);
        t2=(TextView)itemView.findViewById(R.id.t2);
        t3=(TextView)itemView.findViewById(R.id.t3);
    }
}
