package com.example.retrofit.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.retrofit.R;
import com.example.retrofit.SharedPrefManager;

public class DashboardFragment extends Fragment {

    TextView name,email;
    SharedPrefManager sharedPrefManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        name=view.findViewById(R.id.name);
        email=view.findViewById(R.id.email);

        sharedPrefManager = new SharedPrefManager(getActivity());

        String userName= "Hey! " + sharedPrefManager.getUser().getUsername();
        name.setText(userName);
        email.setText(sharedPrefManager.getUser().getEmail());

        return view;
    }
}