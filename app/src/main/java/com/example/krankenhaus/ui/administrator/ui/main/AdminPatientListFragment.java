package com.example.krankenhaus.ui.administrator.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.krankenhaus.R;
import com.example.krankenhaus.databinding.AdminFragmentPatientListBinding;
import com.example.krankenhaus.srccode.entities.Patient;
import com.example.krankenhaus.ui.doctor.ui.main.DoctorViewModel;
import com.example.krankenhaus.ui.doctor.ui.main.PatientAdapter;

import java.util.List;

public class AdminPatientListFragment extends Fragment {
    RecyclerView recyclerView;
    private AdminFragmentPatientListBinding binding;
    private PatientAdapter patientAdapter;
    private AdministratorViewModel administratorViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        administratorViewModel = new ViewModelProvider(requireActivity()).get(AdministratorViewModel.class);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Patient List");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = AdminFragmentPatientListBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = binding.adminPatientList;

        patientAdapter = new PatientAdapter();
        recyclerView.setAdapter(patientAdapter);

        administratorViewModel.getAllPatients().observe(getViewLifecycleOwner(), new Observer<List<Patient>>() {
            @Override
            public void onChanged(List<Patient> patients) {
                patientAdapter.setPatients(patients);
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