package com.example.krankenhaus.ui.administrator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.MenuItem;

import com.example.krankenhaus.R;
import com.example.krankenhaus.srccode.entities.BloodTest;
import com.example.krankenhaus.srccode.entities.Record;
import com.example.krankenhaus.srccode.entities.Visit;
import com.example.krankenhaus.srccode.entities.relations.RecordAndBloodTestAndMRI;
import com.example.krankenhaus.srccode.entities.relations.RecordAndVisitAndPatient;
import com.example.krankenhaus.srccode.entities.relations.VisitAndRecord;
import com.example.krankenhaus.srccode.repository.BedRepository;
import com.example.krankenhaus.srccode.repository.BloodTestRepository;
import com.example.krankenhaus.srccode.repository.PatientRepository;
import com.example.krankenhaus.srccode.repository.RecordRepository;
import com.example.krankenhaus.srccode.repository.VisitRepository;
import com.example.krankenhaus.ui.administrator.ui.main.AdminViewModel;
import com.example.krankenhaus.ui.doctor.DoctorActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.krankenhaus.databinding.ActivityAdministratorBinding;

import java.util.List;

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

        /*VisitRepository visitRepository = VisitRepository.getInstance(this.getApplication());
        RecordRepository recordRepository = RecordRepository.getInstance(this.getApplication());
        BloodTestRepository bloodTestRepository = BloodTestRepository.getInstance(this.getApplication());

        recordRepository.getAllRecords().observe(this, new Observer<List<Record>>() {
            @Override
            public void onChanged(List<Record> records) {
                Visit testVisit = new Visit(records.get(0).getRecordId(),"");
                visitRepository.insertVisit(testVisit);
                Visit testVisit1 = new Visit(records.get(0).getRecordId(),"");
                visitRepository.insertVisit(testVisit1);
                Visit testVisit2 = new Visit(records.get(0).getRecordId(),"");
                visitRepository.insertVisit(testVisit2);
            }
        });

        recordRepository.getAllRecords().observe(this, new Observer<List<Record>>() {
            @Override
            public void onChanged(List<Record> records) {
                BloodTest testBloodTest = new BloodTest(records.get(0).getRecordId(), 0,0,0);
                bloodTestRepository.insertBloodTest(testBloodTest);
                BloodTest testBloodTest2 = new BloodTest(records.get(0).getRecordId(), 1,1,1);
                bloodTestRepository.insertBloodTest(testBloodTest2);
            }
        });

        visitRepository.getAllVisits().observe(this, new Observer<List<Visit>>() {
            @Override
            public void onChanged(List<Visit> visits) {
                visits.size();
            }
        });

        visitRepository.getAllVisitAndRecord().observe(this, new Observer<List<VisitAndRecord>>() {
            @Override
            public void onChanged(List<VisitAndRecord> visitAndRecords) {
                visitAndRecords.size();
            }
        });

        recordRepository.getRecordAndPatientAndVisitByInsuranceNumber("100").observe(this, new Observer<RecordAndVisitAndPatient>() {
            @Override
            public void onChanged(RecordAndVisitAndPatient recordAndVisitAndPatient) {
                recordAndVisitAndPatient.visits.size();
            }
        });

        recordRepository.getAllRecordAndBloodTestAndMRIByInsuranceNumber("100").observe(this, new Observer<RecordAndBloodTestAndMRI>() {
            @Override
            public void onChanged(RecordAndBloodTestAndMRI recordAndBloodTestAndMRI) {
                recordAndBloodTestAndMRI.bloodTest.size();
            }
        });

        recordRepository.getAllRecords().observe(this, new Observer<List<Record>>() {
            @Override
            public void onChanged(List<Record> records) {
                visitRepository.getAllVisitByRecordID(records.get(0).getRecordId()).observe(AdministratorActivity.this, new Observer<List<Visit>>() {
                    @Override
                    public void onChanged(List<Visit> visits) {
                        visitRepository.deleteVisit(visits.get(0));
                        visits.size();
                    }
                });
            }
        });*/
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    public static AdminViewModel obtainViewModel(FragmentActivity activity) {
        return new ViewModelProvider(activity).get(AdminViewModel.class);
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