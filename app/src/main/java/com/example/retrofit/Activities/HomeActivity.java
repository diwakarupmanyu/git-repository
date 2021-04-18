package com.example.retrofit.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.retrofit.Fragments.DashboardFragment;
import com.example.retrofit.Fragments.ProfileFragment;
import com.example.retrofit.Fragments.UsersFragment;
import com.example.retrofit.R;
import com.example.retrofit.SharedPrefManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    SharedPrefManager sharedPrefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottamnav);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        loadfragment(new DashboardFragment());

        sharedPrefManager = new SharedPrefManager(getApplicationContext());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment=null;

        switch (item.getItemId())
        {
            case R.id.dashboard:
                fragment=new DashboardFragment();
                break;
            case R.id.users:
                fragment=new UsersFragment();
                break;
            case R.id.profile:
                fragment = new ProfileFragment();
                break;
        }

        if(fragment!=null)
        {
            loadfragment(fragment);
        }
        return true;
    }

    void loadfragment(Fragment fragment)
    {
        //to attach fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.homerelativelayout,fragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logoutmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
                case R.id.logout:
                logoutUser();
                break;

            case R.id.deleteAccount:
                deleteAccount();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void deleteAccount()
    {

    }

    private void logoutUser()
    {
        sharedPrefManager.logout();
        Intent intent = new Intent(HomeActivity.this,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

        Toast.makeText(this,"You have been logged out!",Toast.LENGTH_SHORT).show();

    }
}