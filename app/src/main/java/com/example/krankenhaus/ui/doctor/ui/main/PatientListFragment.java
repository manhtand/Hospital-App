package com.example.krankenhaus.ui.doctor.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.krankenhaus.R;
import com.example.krankenhaus.databinding.FragmentPatientListBinding;
import com.example.krankenhaus.srccode.entities.Patient;
import com.example.krankenhaus.srccode.entities.relations.PatientAndBed;
import com.example.krankenhaus.ui.doctor.DoctorActivity;

import java.util.List;

public class PatientListFragment extends Fragment {
    RecyclerView recyclerView;
    private FragmentPatientListBinding binding;
    private PatientAdapter patientAdapter;
    private DoctorViewModel doctorViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Patient List");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPatientListBinding.inflate(inflater, container, false);
        doctorViewModel = new ViewModelProvider(requireActivity()).get(DoctorViewModel.class);
        View root = binding.getRoot();

        recyclerView = binding.patientList;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        patientAdapter = new PatientAdapter();
        recyclerView.setAdapter(patientAdapter);

        doctorViewModel.getAllPatientAndBeds().observe(getViewLifecycleOwner(), new Observer<List<PatientAndBed>>() {
            @Override
            public void onChanged(List<PatientAndBed> patientAndBeds) {
                patientAdapter.setPatientAndBedList(patientAndBeds);
            }
        });

        patientAdapter.setOnItemClickListener(new PatientAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(PatientAndBed patientAndBed) {
                PatientInfoFragment patientInfoFragment = new PatientInfoFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.setReorderingAllowed(true);
                ft.addToBackStack(null);
                ft.replace(R.id.nav_host_fragment_activity_doctor, patientInfoFragment);
                ft.commit();

                doctorViewModel.setPatient(patientAndBed.patient);
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