package com.example.apidatafetch.AdapterClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apidatafetch.ModelClasses.FetchResponse;
import com.example.apidatafetch.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> {

    List<FetchResponse> list;
    Context context;


    public DataAdapter(List<FetchResponse> list,Context context)
    {
        this.list =list;
        this.context = context;

    }

    @NonNull
    @Override
    public DataAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardlayout,null);
        DataAdapter.MyViewHolder viewHolder = new DataAdapter.MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DataAdapter.MyViewHolder holder, int position) {

        holder.userid.setText(list.get(position).getId());
        holder.title.setText(list.get(position).getTitle());

        Picasso.with(context)
                .load(list.get(position).getUrl())
                .placeholder(R.drawable.ic_launcher_background)
                .fit()
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView userid,title;
        ImageView image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            userid = itemView.findViewById(R.id.userid);
            title = itemView.findViewById(R.id.title);
            image = itemView.findViewById(R.id.logo_image);
        }
    }
}
