package com.example.krankenhaus.ui.doctor.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.krankenhaus.databinding.FragmentPatientListBinding;
import com.example.krankenhaus.srccode.entities.Patient;

import java.util.List;

public class PatientListFragment extends Fragment {
    RecyclerView recyclerView;
    private FragmentPatientListBinding binding;
    private PatientAdapter patientAdapter;
    private DoctorDashboardViewModel doctorDashboardViewModel = new ViewModelProvider(requireActivity()).get(DoctorDashboardViewModel.class);

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentPatientListBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = binding.patientList;

        patientAdapter = new PatientAdapter();
        recyclerView.setAdapter(patientAdapter);

        doctorDashboardViewModel.getAllPatient().observe(getViewLifecycleOwner(), new Observer<List<Patient>>() {
            @Override
            public void onChanged(List<Patient> patients) {
                patientAdapter.setPatients(patients);
            }
        });

        return root;
    }
}