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
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>
{
    List<FetchResponse> list;
    Context context;

    public RecyclerAdapter(List<FetchResponse> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardlayout,null,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position)
    {
        // holder.titleid.setText(list.get(position).getId());
        holder.title.setText(list.get(position).getTitle());

       /* Picasso.with(context)
                .load(list.get(position).getUrl())
                .placeholder(R.drawable.ic_launcher_background)
                .fit()
                .into(holder.image); */
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build()
                .load(list.get(position).getUrl())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView title,titleid;
        ImageView image;
        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
           // titleid = itemView.findViewById(R.id.titleid);
            title = itemView.findViewById(R.id.title);
            image = itemView.findViewById(R.id.imagelogo);

        }
    }
}
