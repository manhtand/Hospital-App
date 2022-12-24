package com.example.krankenhaus.ui.doctor.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.krankenhaus.R;
import com.example.krankenhaus.databinding.FragmentDoctorNotificationsBinding;
import com.example.krankenhaus.ui.doctor.DoctorActivity;

public class DoctorNotificationsFragment extends Fragment {

    private FragmentDoctorNotificationsBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DoctorViewModel doctorNotificationsViewModel = new ViewModelProvider(this).get(DoctorViewModel.class);

        binding = FragmentDoctorNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ((DoctorActivity) getActivity()).setActionBarTitle("Notifications");

        return root;
    }

    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}