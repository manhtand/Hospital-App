package com.example.krankenhaus.ui.loginscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.example.krankenhaus.srccode.entities.Bed;
import com.example.krankenhaus.srccode.entities.MRI;
import com.example.krankenhaus.srccode.entities.Patient;
import com.example.krankenhaus.srccode.entities.Record;
import com.example.krankenhaus.srccode.entities.Visit;
import com.example.krankenhaus.srccode.entities.relations.PatientAndBed;
import com.example.krankenhaus.srccode.entities.relations.VisitAndRecord;
import com.example.krankenhaus.srccode.repository.BedRepository;
import com.example.krankenhaus.srccode.repository.MRIRepository;
import com.example.krankenhaus.srccode.repository.PatientRepository;
import com.example.krankenhaus.srccode.repository.RecordRepository;
import com.example.krankenhaus.srccode.repository.VisitRepository;
import com.example.krankenhaus.ui.administrator.AdministratorActivity;
import com.example.krankenhaus.ui.doctor.DoctorActivity;
import com.example.krankenhaus.ui.doctor.ui.main.DoctorViewModel;
import com.example.krankenhaus.ui.menubar.MainActivity;

import android.view.View;
import android.widget.Button;

import com.example.krankenhaus.R;
import com.example.krankenhaus.databinding.ActivityLogInScreenBinding;
import com.example.krankenhaus.ui.service.ServiceActivity;

import java.time.LocalDate;
import java.util.List;

public class LogInScreenActivity extends AppCompatActivity {
    private ActivityLogInScreenBinding binding;
    private int tmpsize;
    private Record record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);

        binding = ActivityLogInScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnDoctor.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openActivityDoctor();
            }
        });

        binding.btnAdministrator.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {openActivityAdministrator();}
        });

        binding.btnService.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) { openActivityService();
            }
        });
        
        BedRepository bedRepository = BedRepository.getInstance(this.getApplication());
        for (int i = 1; i <= 21; i++) {
            bedRepository.insertBed(new Bed(i));
        }
        // TEST
        PatientRepository patientRepository = PatientRepository.getInstance(this.getApplication());
        VisitRepository visitRepository = VisitRepository.getInstance(this.getApplication());
        RecordRepository recordRepository = RecordRepository.getInstance(this.getApplication());

        Patient testPatient = new Patient("100", 1, "Le", LocalDate.of(2001, 10, 02), "Hanoi", "Hanoi", "123", "Company");
        patientRepository.insertPatient(testPatient);

        patientRepository.getAllPatients().observe(this, new Observer<List<Patient>>() {
            @Override
            public void onChanged(List<Patient> patients) {
                tmpsize = patients.size();
            }
        });
        Record testRecord = new Record("100");
        recordRepository.insertRecord(testRecord);

        recordRepository.getAllRecords().observe(this, new Observer<List<Record>>() {
            @Override
            public void onChanged(List<Record> records) {
                tmpsize = records.size();
            }
        });

        patientRepository.insertPatient(new Patient("123", 2, "Doan", LocalDate.of(2001, 10, 02), "Hanoi", "Hanoi", "123", "Compny"));
        Record record = new Record("123");
        recordRepository.insertRecord(record);
        record.setRecordId(1000);

        MRIRepository mriRepository = MRIRepository.getInstance(this.getApplication());
        recordRepository.getAllRecords().observe(this, new Observer<List<Record>>() {
            @Override
            public void onChanged(List<Record> records) {
                mriRepository.insertMRI(new MRI(records.get(0).getRecordId(),null));
            }
        });

        /*
        //Doctor Test
        VisitRepository visitRepository = VisitRepository.getInstance(this.getApplication());
        RecordRepository recordRepository = RecordRepository.getInstance(this.getApplication());
        recordRepository.getAllRecords().observe(this, new Observer<List<Record>>() {
            @Override
            public void onChanged(List<Record> records) {
                Visit testVisit = new Visit(records.get(0).getRecordId(),"");
                visitRepository.insertVisit(testVisit);
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

        recordRepository.getAllRecordAndBloodTestAndMRIByInsuranceNumber("100").observe(this, new Observer<List<RecordAndBloodTestAndMRI>>() {
            @Override
            public void onChanged(List<RecordAndBloodTestAndMRI> recordAndBloodTestAndMRIS) {
                recordAndBloodTestAndMRIS.size();
            }
        });

        recordRepository.getAllRecords().observe(this, new Observer<List<Record>>() {
            @Override
            public void onChanged(List<Record> records) {
                recordid = records.get(0).getRecordId();
                visitRepository.getAllVisitByRecordID(recordid).observe(DoctorActivity.this, new Observer<List<Visit>>() {
                    @Override
                    public void onChanged(List<Visit> visits) {
                        visits.size();
                    }
                });
            }
        });
        PatientRepository patientRepository = PatientRepository.getInstance(getApplication());

        patientRepository.getPatientAndRecordByInsuranceNumber("100").observe(this, new Observer<PatientAndRecord>() {
            @Override
            public void onChanged(PatientAndRecord patientAndRecord) {
                recordid = patientAndRecord.record.getRecordId();
            }
        });
        recordRepository.getAllRecordAndBloodTestAndMRIByInsuranceNumber("100").observe(this, new Observer<List<RecordAndBloodTestAndMRI>>() {
            @Override
            public void onChanged(List<RecordAndBloodTestAndMRI> recordAndBloodTestAndMRIS) {
                recordAndBloodTestAndMRIS.size();
            }
        });*/
    }

    public void openActivityDoctor(){
        Intent intent = new Intent(this, DoctorActivity.class);
        startActivity(intent);
    }
    public void openActivityAdministrator(){
        Intent intent = new Intent(this, AdministratorActivity.class);
        startActivity(intent);
    }
    public void openActivityService(){
        Intent intent = new Intent(this, ServiceActivity.class);
        startActivity(intent);
    }
}