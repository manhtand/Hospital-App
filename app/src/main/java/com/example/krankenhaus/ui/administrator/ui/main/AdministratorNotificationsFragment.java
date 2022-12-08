package com.example.krankenhaus.ui.administrator.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.krankenhaus.R;
import com.example.krankenhaus.databinding.FragmentAdministratorNotificationsBinding;
import com.example.krankenhaus.ui.administrator.AdministratorActivity;
import com.example.krankenhaus.ui.administrator.ui.main.AdministratorNotificationsViewModel;

public class AdministratorNotificationsFragment extends Fragment {

    private FragmentAdministratorNotificationsBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        AdministratorNotificationsViewModel administratorNotificationsViewModel = new ViewModelProvider(this).get(AdministratorNotificationsViewModel.class);

        binding = FragmentAdministratorNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.messageAdministratorNotifications;
        administratorNotificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        ((AdministratorActivity) getActivity()).setActionBarTitle("Notifications");

        return root;
    }

    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}