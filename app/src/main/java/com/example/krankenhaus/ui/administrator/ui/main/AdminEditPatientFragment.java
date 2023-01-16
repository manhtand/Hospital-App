package com.example.krankenhaus.ui.administrator.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.krankenhaus.R;
import com.example.krankenhaus.databinding.FragmentEditPatientBinding;

public class AdminEditPatientFragment extends Fragment {
    private FragmentEditPatientBinding binding;
    private AdminViewModel administratorViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        administratorViewModel = new ViewModelProvider(requireActivity()).get(AdminViewModel.class);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Patient Information");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentEditPatientBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.editPatientInfoSaveInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnToAdminPatientList();
            }
        });

        binding.editPatientInfoCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnToAdminPatientList();
            }
        });

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    public void returnToAdminPatientList() {
        AdminPatientListFragment adminPatientListFragment = new AdminPatientListFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.nav_host_fragment_activity_administrator, adminPatientListFragment);
        ft.commit();
    }
}