package com.example.krankenhaus.ui.administrator.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.krankenhaus.databinding.FragmentAdministratorDashboardBinding;

import com.example.krankenhaus.R;
import com.example.krankenhaus.ui.administrator.AdministratorActivity;
import com.example.krankenhaus.ui.administrator.ui.main.AdministratorViewModel;
import com.example.krankenhaus.ui.doctor.DoctorActivity;

public class AdministratorDashboardFragment extends Fragment {

    private FragmentAdministratorDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        AdministratorViewModel administratorViewModel = new ViewModelProvider(this).get(AdministratorViewModel.class);

        binding = FragmentAdministratorDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ((AdministratorActivity) getActivity()).setActionBarTitle("Dashboard");

        return root;
    }

    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}