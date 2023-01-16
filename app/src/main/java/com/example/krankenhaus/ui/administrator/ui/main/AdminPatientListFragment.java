package com.example.krankenhaus.ui.administrator.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.krankenhaus.R;
import com.example.krankenhaus.databinding.AdminFragmentPatientListBinding;

import com.example.krankenhaus.srccode.entities.relations.PatientAndBed;
import com.example.krankenhaus.srccode.entities.relations.PatientAndRecord;
import com.example.krankenhaus.srccode.entities.relations.RecordAndBloodTestAndMRI;
import com.example.krankenhaus.srccode.entities.relations.RecordAndVisitAndPatient;
import com.example.krankenhaus.srccode.repository.PatientRepository;
import com.example.krankenhaus.ui.doctor.ui.main.PatientAdapter;

import java.util.List;

public class AdminPatientListFragment extends Fragment {
    RecyclerView recyclerView;
    private AdminFragmentPatientListBinding binding;
    private PatientAdapter patientAdapter;
    private AdminViewModel administratorViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        administratorViewModel = new ViewModelProvider(requireActivity()).get(AdminViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Patient List");

        binding = AdminFragmentPatientListBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = binding.adminPatientList;

        patientAdapter = new PatientAdapter();
        recyclerView.setAdapter(patientAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));

        administratorViewModel.getAllPatientAndBeds().observe(getViewLifecycleOwner(), new Observer<List<PatientAndBed>>() {
            @Override
            public void onChanged(List<PatientAndBed> patientAndBeds) {
                patientAdapter.setPatientAndBedList(patientAndBeds);
            }
        });

        binding.addPatientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminAddPatientFragment addPatientFragment = new AdminAddPatientFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.setReorderingAllowed(true);
                ft.addToBackStack(null);
                ft.replace(R.id.nav_host_fragment_activity_administrator, addPatientFragment);
                ft.commit();
            }
        });

        patientAdapter.setOnItemClickListener(new PatientAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(PatientAndBed patientAndBed) {
                administratorViewModel.setPatient(patientAndBed.patient);
                administratorViewModel.getPatientAndRecordByInsuranceNumber(patientAndBed.patient.getInsuranceNumber()).observe(getViewLifecycleOwner(), new Observer<PatientAndRecord>() {
                    @Override
                    public void onChanged(PatientAndRecord patientAndRecord) {
                        administratorViewModel.setPatientAndRecord(patientAndRecord);
                    }
                });

                administratorViewModel.getRecordAndBloodTestAndMRIByInsuranceNumber(patientAndBed.patient.getInsuranceNumber()).observe(getViewLifecycleOwner(), new Observer<RecordAndBloodTestAndMRI>() {
                    @Override
                    public void onChanged(RecordAndBloodTestAndMRI recordAndBloodTestAndMRIS) {
                        administratorViewModel.setRecordAndBloodTestAndMRI(recordAndBloodTestAndMRIS);
                    }
                });

                administratorViewModel.getRecordAndVisitAndPatientByInsuranceNumber(patientAndBed.patient.getInsuranceNumber()).observe(getViewLifecycleOwner(), new Observer<RecordAndVisitAndPatient>() {
                    @Override
                    public void onChanged(RecordAndVisitAndPatient recordAndVisitAndPatient) {
                        administratorViewModel.setRecordAndVisitAndPatient(recordAndVisitAndPatient);
                    }
                });

                SystemClock.sleep(50);

                AdminPatientInfoFragment adminPatientInfoFragment = new AdminPatientInfoFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.setReorderingAllowed(true);
                ft.addToBackStack(null);
                ft.replace(R.id.nav_host_fragment_activity_administrator, adminPatientInfoFragment);
                ft.commit();
            }
        });
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}