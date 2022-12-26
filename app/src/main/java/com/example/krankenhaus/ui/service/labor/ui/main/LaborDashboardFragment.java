package com.example.krankenhaus.ui.service.labor.ui.main;

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

import com.example.krankenhaus.R;
import com.example.krankenhaus.databinding.FragmentLaborDashboardBinding;
import com.example.krankenhaus.ui.doctor.DoctorActivity;
import com.example.krankenhaus.ui.service.labor.LaborActivity;

public class LaborDashboardFragment extends Fragment {

    private FragmentLaborDashboardBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLaborDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ((LaborActivity) getActivity()).setActionBarTitle("Dashboard");

        binding.laborBloodTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { openFragmentBloodTestList(); }
        });

        binding.laborMrtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { openFragmentMRTList(); }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void openFragmentBloodTestList() {
        BloodTestListFragment bloodTestListFragment = new BloodTestListFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setReorderingAllowed(true);
        ft.addToBackStack(null);
        ft.replace(R.id.nav_host_fragment_activity_labor, bloodTestListFragment);
        ft.commit();
    }

    public void openFragmentMRTList() {
        MrtListFragment mrtListFragment = new MrtListFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setReorderingAllowed(true);
        ft.addToBackStack(null);
        ft.replace(R.id.nav_host_fragment_activity_labor, mrtListFragment);
        ft.commit();
    }
}