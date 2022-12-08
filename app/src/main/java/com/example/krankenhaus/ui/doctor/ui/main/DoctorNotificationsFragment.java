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
        DoctorNotificationsViewModel doctorNotificationsViewModel = new ViewModelProvider(this).get(DoctorNotificationsViewModel.class);

        binding = FragmentDoctorNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.messageDoctorNotifications;
        doctorNotificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        ((DoctorActivity) getActivity()).setActionBarTitle("Notifications");

        return root;
    }

    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}