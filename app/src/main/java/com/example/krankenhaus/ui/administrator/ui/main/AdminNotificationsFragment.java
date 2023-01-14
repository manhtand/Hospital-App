package com.example.krankenhaus.ui.administrator.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.krankenhaus.databinding.FragmentAdministratorNotificationsBinding;
import com.example.krankenhaus.ui.administrator.AdministratorActivity;

public class AdminNotificationsFragment extends Fragment {

    private FragmentAdministratorNotificationsBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        AdminViewModel administratorViewModel = new ViewModelProvider(this).get(AdminViewModel.class);

        binding = FragmentAdministratorNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ((AdministratorActivity) getActivity()).setActionBarTitle("Notifications");

        return root;
    }

    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}