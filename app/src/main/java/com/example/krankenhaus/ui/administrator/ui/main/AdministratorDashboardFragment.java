package com.example.krankenhaus.ui.administrator.ui.main;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAdministratorDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ((AdministratorActivity) getActivity()).setActionBarTitle("Dashboard");

        binding.administratorBedListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { openFragmentPatientList(); };
        });

        binding.administratorPatientListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { openFragmentBedList(); };
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void openFragmentPatientList() {
        AdminPatientListFragment adminPatientListFragment = new AdminPatientListFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setReorderingAllowed(true);
        ft.addToBackStack(null);
        ft.replace(R.id.nav_host_fragment_activity_administrator, adminPatientListFragment);
        ft.commit();
    }

    public void openFragmentBedList() {
        BedListFragment bedListFragment = new BedListFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setReorderingAllowed(true);
        ft.addToBackStack(null);
        ft.replace(R.id.nav_host_fragment_activity_administrator, bedListFragment);
        ft.commit();
    }
}