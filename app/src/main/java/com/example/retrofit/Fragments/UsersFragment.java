package com.example.retrofit.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.retrofit.ModelResponse.FetchUserResponse;
import com.example.retrofit.ModelResponse.User;
import com.example.retrofit.R;
import com.example.retrofit.RetrofitClient;
import com.example.retrofit.UserAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersFragment extends Fragment {

   RecyclerView recyclerView;
   List<User> userList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_users, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //retrofit
        Call<FetchUserResponse> call = RetrofitClient.getInstance().getApi().fetchUser();
        call.enqueue(new Callback<FetchUserResponse>() {
            @Override
            public void onResponse(Call<FetchUserResponse> call, Response<FetchUserResponse> response) {
                if(response.isSuccessful())
                {
                    userList=response.body().getUserList();
                    recyclerView.setAdapter(new UserAdapter(getActivity(),userList));
                }
                else
                {
                    Toast.makeText(getActivity(),response.body().getError(),Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<FetchUserResponse> call, Throwable t)
            {
                Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}