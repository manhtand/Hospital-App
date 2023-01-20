package com.example.krankenhaus.ui.administrator.ui.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.krankenhaus.R;
import com.example.krankenhaus.databinding.FragmentAdminPatientInfoBinding;
import com.example.krankenhaus.srccode.entities.Patient;
import com.example.krankenhaus.srccode.entities.Record;
import com.example.krankenhaus.srccode.entities.relations.PatientAndRecord;
import com.example.krankenhaus.srccode.entities.relations.RecordAndBloodTestAndMRI;
import com.example.krankenhaus.srccode.entities.relations.RecordAndVisitAndPatient;

import java.time.format.DateTimeFormatter;

public class AdminPatientInfoFragment extends Fragment {
    private Patient patient;
    private Record record;
    private RecordAndVisitAndPatient recordAndVisitAndPatient;
    private RecordAndBloodTestAndMRI recordAndBloodTestAndMRI;

    private AdminViewModel adminViewModel;
    private FragmentAdminPatientInfoBinding binding;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adminViewModel = new ViewModelProvider(requireActivity()).get(AdminViewModel.class);

        adminViewModel.getPatient().observe(getActivity(), new Observer<Patient>() {
            @Override
            public void onChanged(Patient p) {
                patient = p;
            }
        });

        adminViewModel.getPatientAndRecord().observe(getActivity(), new Observer<PatientAndRecord>() {
            @Override
            public void onChanged(PatientAndRecord patientAndRecord) {
                record = patientAndRecord.record;
            }
        });
        adminViewModel.getRecordAndVisitAndPatient().observe(getActivity(), new Observer<RecordAndVisitAndPatient>() {
            @Override
            public void onChanged(RecordAndVisitAndPatient input) {
                recordAndVisitAndPatient = input;
            }
        });
        adminViewModel.getRecordAndBloodTestAndMRI().observe(getActivity(), new Observer<RecordAndBloodTestAndMRI>() {
            @Override
            public void onChanged(RecordAndBloodTestAndMRI input) {
                recordAndBloodTestAndMRI = input;
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Patient Information");

        binding = FragmentAdminPatientInfoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        setPatientData(patient);

        setButton();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void setPatientData(Patient patient) {
        binding.adminPatientInfoName.setText(patient.getName());
        binding.adminPatientInfoRecordId.setText(String.valueOf(record.getRecordId()));
        binding.adminPatientInfoBedNumber.setText(String.valueOf(patient.getBedNumber()));
        binding.adminPatientInfoDateOfBirth.setText(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(patient.getDateOfBirth()));
        binding.adminPatientInfoAddress.setText(patient.getAddress());
        binding.adminPatientInfoZipCode.setText(patient.getZipCode());
        binding.adminPatientInfoPlaceOfResidence.setText(patient.getPlaceOfResidence());
        binding.adminPatientInfoInsuranceNumber.setText(patient.getInsuranceNumber());
        binding.adminPatientInfoHealthInsuranceCompany.setText(patient.getHealthInsuranceCompany());
        binding.adminPatientInfoAdmissionDate.setText(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(patient.getAdmissionDate()));

        if (patient.isDischarged()) {
            binding.adminPatientHealthStatus.setText("Recoverd");
        }
        else {
            binding.adminPatientHealthStatus.setText("Not Recoverd");
        }
    }

    public void setButton(){
        binding.adminDischargePatientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminViewModel.dischargePatient(recordAndVisitAndPatient,recordAndBloodTestAndMRI);
                SystemClock.sleep(100);

                Toast.makeText(getActivity(), "Patient released", Toast.LENGTH_LONG).show();

                AdminPatientListFragment adminPatientListFragment = new AdminPatientListFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.nav_host_fragment_activity_administrator, adminPatientListFragment);
                ft.commit();
            }
        });

        binding.adminEditPatientInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdminEditPatientFragment editPatientFragment = new AdminEditPatientFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.setReorderingAllowed(true);
                ft.addToBackStack(null);
                ft.replace(R.id.nav_host_fragment_activity_administrator, editPatientFragment);
                ft.commit();
            }
        });
    }
}