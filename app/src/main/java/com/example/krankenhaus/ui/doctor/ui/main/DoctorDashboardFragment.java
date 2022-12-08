package com.example.krankenhaus.ui.doctor.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.krankenhaus.databinding.FragmentDoctorDashboardBinding;

import com.example.krankenhaus.R;
import com.example.krankenhaus.ui.doctor.DoctorActivity;

public class DoctorDashboardFragment extends Fragment {

    private FragmentDoctorDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DoctorDashboardViewModel doctorDashboardViewModel = new ViewModelProvider(this).get(DoctorDashboardViewModel.class);

        binding = FragmentDoctorDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.messageDoctorDashboard;
        doctorDashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        ((DoctorActivity) getActivity()).setActionBarTitle("Dashboard");

        return root;
    }

    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}