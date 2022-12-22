package com.example.krankenhaus.ui.doctor.ui.main;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.krankenhaus.databinding.FragmentDoctorDashboardBinding;

import com.example.krankenhaus.R;
import com.example.krankenhaus.srccode.entities.Patient;
import com.example.krankenhaus.ui.doctor.DoctorActivity;

public class DoctorDashboardFragment extends Fragment {

    private FragmentDoctorDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DoctorDashboardViewModel doctorDashboardViewModel = new ViewModelProvider(this).get(DoctorDashboardViewModel.class);

        binding = FragmentDoctorDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ((DoctorActivity) getActivity()).setActionBarTitle("Dashboard");

        binding.patientListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { openFragmentPatientList(); }
        });

        binding.visitListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragmentVisitList();
            }
        });

        return root;
    }

    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void openFragmentPatientList() {
        PatientListFragment patientListFragment = new PatientListFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.doctor_dashboard_layout, patientListFragment, patientListFragment.getTag())
                .commit();
    }

    public void openFragmentVisitList() {
        VisitListFragment visitListFragment = new VisitListFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.doctor_dashboard_layout, visitListFragment, visitListFragment.getTag())
                .commit();
    }
}