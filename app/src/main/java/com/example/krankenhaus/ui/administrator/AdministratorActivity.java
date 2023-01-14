package com.example.krankenhaus.ui.administrator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.krankenhaus.R;
import com.example.krankenhaus.srccode.repository.BedRepository;
import com.example.krankenhaus.srccode.repository.PatientRepository;
import com.example.krankenhaus.ui.administrator.ui.main.AdminViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.krankenhaus.databinding.ActivityAdministratorBinding;

public class AdministratorActivity extends AppCompatActivity {

    private ActivityAdministratorBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);

        binding = ActivityAdministratorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        BottomNavigationView navigationView = findViewById(R.id.administrator_navigation_view);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.administrator_navigation_home, R.id.administrator_navigation_dashboard, R.id.administrator_navigation_notifications).build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_administrator);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.administratorNavigationView, navController);

        getSupportActionBar().setTitle("Administrator");

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        obtainViewModel(this);
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    public static AdminViewModel obtainViewModel(FragmentActivity activity) {
        BedRepository bedRepository = BedRepository.getInstance(activity.getApplication());
        PatientRepository patientRepository = PatientRepository.getInstance(activity.getApplication());
        AdminViewModel administratorViewModel = new ViewModelProvider(activity).get(AdminViewModel.class);
        administratorViewModel.setRepository(patientRepository, bedRepository);


        return administratorViewModel;
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