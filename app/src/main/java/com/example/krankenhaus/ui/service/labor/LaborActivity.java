package com.example.krankenhaus.ui.service.labor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.krankenhaus.databinding.ActivityLaborBinding;
import com.example.krankenhaus.R;

import com.example.krankenhaus.srccode.entities.BloodTest;
import com.example.krankenhaus.srccode.entities.MRI;
import com.example.krankenhaus.srccode.entities.Patient;
import com.example.krankenhaus.srccode.entities.Record;
import com.example.krankenhaus.srccode.repository.BloodTestRepository;
import com.example.krankenhaus.srccode.repository.MRIRepository;
import com.example.krankenhaus.srccode.repository.PatientRepository;
import com.example.krankenhaus.srccode.repository.RecordRepository;
import com.example.krankenhaus.ui.service.labor.ui.main.LaborViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.opencsv.CSVReader;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class LaborActivity extends AppCompatActivity {
    LaborViewModel laborViewModel;
    private ActivityLaborBinding binding;
    private List<BloodTest> bloodTestSource = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);

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

        obtainViewModel(this);

        /* Test

        MRIRepository mriRepository = MRIRepository.getInstance(this.getApplication());
        RecordRepository recordRepository = RecordRepository.getInstance(this.getApplication());

        recordRepository.getAllRecords().observe(this, new Observer<List<Record>>() {
            @Override
            public void onChanged(List<Record> records) {
                mriRepository.insertMRI(new MRI(records.get(0).getRecordId(),false,null));
            }
        });

        PatientRepository patientRepository = PatientRepository.getInstance(this.getApplication());
        patientRepository.getAllPatients().observe(this, new Observer<List<Patient>>() {
            @Override
            public void onChanged(List<Patient> patients) {
                patients.size();
            }
        });*/
        readBloodTestCSV();
        laborViewModel = new ViewModelProvider(this).get(LaborViewModel.class);
        laborViewModel.setBloodTestSource(bloodTestSource);
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    public static LaborViewModel obtainViewModel(FragmentActivity activity){
        return new ViewModelProvider(activity).get(LaborViewModel.class);
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

    private void readBloodTestCSV() {
        InputStream is = getResources().openRawResource(R.raw.bloodtest);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is)
        );

        /*String line = "";
        while (true) {
            try {
                if (((line = reader.readLine()) != null)) {
                    String[] tokens = line.split(";");
                    BloodTest bloodTest = new BloodTest(-1, false, LocalDateTime.of(1,1,1,1,1), -1, -1, -1);
                    bloodTest.setLeukocytesPerNanoLiter(Double.parseDouble(tokens[0]));
                    bloodTest.setLymphocytesLeukocytesRatio(Double.parseDouble(tokens[1]));
                    bloodTest.setLymphocytesInHundredPerNanoLiter(Double.parseDouble(tokens[2]));
                    bloodTestSource.add(bloodTest);
                }
            } catch (IOException e) {
                Log.e(getAttributionTag(), "Error read line");
                e.printStackTrace();
            }
        }*/
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(";");
                BloodTest bloodTest = new BloodTest(-1, false, LocalDateTime.of(1,1,1,1,1), -1, -1, -1);
                bloodTest.setLeukocytesPerNanoLiter(Double.parseDouble(tokens[0]));
                bloodTest.setLymphocytesLeukocytesRatio(Double.parseDouble(tokens[1]));
                bloodTest.setLymphocytesInHundredPerNanoLiter(Double.parseDouble(tokens[2]));
                bloodTestSource.add(bloodTest);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}