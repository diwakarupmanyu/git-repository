package com.example.registration.AdapterClass;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.registration.Activities.UpdateData;
import com.example.registration.DBHelperClasses.DatabaseHelper;
import com.example.registration.ModelClass.UserDataResponse;
import com.example.registration.R;

import java.util.ArrayList;

public class DataFetchAdapter extends BaseAdapter {


    ArrayList<UserDataResponse> dataArrayList;

    public DataFetchAdapter(ArrayList<UserDataResponse> dataArrayList) {
        this.dataArrayList = dataArrayList;
    }

    @Override
    public int getCount() {
        return dataArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_detail_layout,null,false);

        TextView showId,showName,showAge,showCity,showPhone,showGmail;
        Button updateData,deleteData;
        DatabaseHelper databaseHelper;

        databaseHelper = new DatabaseHelper(view.getContext());

        showId = view.findViewById(R.id.showId);
        showName = view.findViewById(R.id.showName);
        showAge = view.findViewById(R.id.showAge);
        showCity = view.findViewById(R.id.showCity);
        showPhone = view.findViewById(R.id.showPhone);
        showGmail = view.findViewById(R.id.showGmail);
        updateData = view.findViewById(R.id.updateData);
        deleteData = view.findViewById(R.id.deleteData);

        UserDataResponse dataResponse = dataArrayList.get(position);

        showId.setText("id: "+dataResponse.getId());
        showName.setText("name: "+dataResponse.getName());
        showAge.setText("age: "+dataResponse.getAge());
        showCity.setText("city: "+dataResponse.getCity());
        showPhone.setText("phone: "+dataResponse.getPhone());
        showGmail.setText("mail: "+dataResponse.getGmail());



        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(), UpdateData.class));
            }
        });

        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             /*   dataResponse.getId();
                int result = databaseHelper.deleteData(dataResponse);

                if (result > 0) {
                    Toast.makeText(v.getContext(), "Data deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(v.getContext(), "Data not deleted", Toast.LENGTH_SHORT).show();
                }
            */
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

                builder.setTitle("Delete");
                builder.setMessage("Are you sure to delete this id ?");
                builder.setIcon(R.drawable.ic_delete);
                builder.setCancelable(false);

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        UserDataResponse dataResponse = dataArrayList.get(position);
                        int result = databaseHelper.deleteData(dataResponse.getId());

                        if (result > 0) {
                            Toast.makeText(v.getContext(), "Data deleted", Toast.LENGTH_SHORT).show();
                            dataArrayList.remove(dataResponse);
                            notifyDataSetChanged();

                        } else {
                            Toast.makeText(v.getContext(), "Data not deleted", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("No",null);
                builder.show();
            }
        });

        return view;
    }
}
