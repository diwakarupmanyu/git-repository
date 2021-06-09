package com.example.registration.AdapterClass;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
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

        showId = view.findViewById(R.id.showId);
        showName = view.findViewById(R.id.showName);
        showAge = view.findViewById(R.id.showAge);
        showCity = view.findViewById(R.id.showCity);
        showPhone = view.findViewById(R.id.showPhone);
        showGmail = view.findViewById(R.id.showGmail);

        UserDataResponse dataResponse = dataArrayList.get(position);

        showId.setText("id: "+dataResponse.getId());
        showName.setText("name: "+dataResponse.getName());
        showAge.setText("age: "+dataResponse.getAge());
        showCity.setText("city: "+dataResponse.getCity());
        showPhone.setText("phone: "+dataResponse.getPhone());
        showGmail.setText("mail: "+dataResponse.getGmail());

        return view;
    }
}
