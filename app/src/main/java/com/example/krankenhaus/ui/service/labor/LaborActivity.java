package com.example.krankenhaus.ui.service.labor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.krankenhaus.databinding.ActivityLaborBinding;
import com.example.krankenhaus.R;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class LaborActivity extends AppCompatActivity {

    private ActivityLaborBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLaborBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        BottomNavigationView navigationView = findViewById(R.id.labor_navigation_view);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.labor_navigation_home, R.id.labor_navigation_dashboard, R.id.labor_navigation_notifications).build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_labor);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.laborNavigationView, navController);

        getSupportActionBar().setTitle("Labor");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStackImmediate();
        }
        else {
            super.onBackPressed();
        }
    }
}