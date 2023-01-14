package com.example.krankenhaus.ui.doctor.ui.main;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.krankenhaus.databinding.FragmentDoctorDashboardBinding;

import com.example.krankenhaus.R;
import com.example.krankenhaus.ui.doctor.DoctorActivity;

public class DoctorDashboardFragment extends Fragment {

    private FragmentDoctorDashboardBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDoctorDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ((DoctorActivity) getActivity()).setActionBarTitle("Doctor");

        binding.doctorPatientListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { openFragmentPatientList(); }
        });

        binding.doctorVisitListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { openFragmentVisitList(); }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void openFragmentPatientList() {
        DoctorPatientListFragment doctorPatientListFragment = new DoctorPatientListFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setReorderingAllowed(true);
        ft.addToBackStack(null);
        ft.replace(R.id.nav_host_fragment_activity_doctor, doctorPatientListFragment);
        ft.commit();
    }

    public void openFragmentVisitList() {
        VisitListFragment visitListFragment = new VisitListFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setReorderingAllowed(true);
        ft.addToBackStack(null);
        ft.replace(R.id.nav_host_fragment_activity_doctor, visitListFragment);
        ft.commit();
    }
}