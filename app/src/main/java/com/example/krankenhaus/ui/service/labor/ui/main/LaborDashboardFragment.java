package com.example.krankenhaus.ui.service.labor.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.krankenhaus.R;
import com.example.krankenhaus.databinding.FragmentLaborDashboardBinding;
import com.example.krankenhaus.ui.doctor.DoctorActivity;
import com.example.krankenhaus.ui.service.labor.LaborActivity;

public class LaborDashboardFragment extends Fragment {

    private FragmentLaborDashboardBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LaborViewModel laborDashboardViewModel = new ViewModelProvider(this).get(LaborViewModel.class);

        binding = FragmentLaborDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ((LaborActivity) getActivity()).setActionBarTitle("Dashboard");

        return root;
    }

    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}