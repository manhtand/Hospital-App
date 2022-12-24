package com.example.krankenhaus.ui.doctor.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewSwitcher;

import com.example.krankenhaus.databinding.FragmentPatientInfoBinding;

import com.example.krankenhaus.R;

public class PatientInfoFragment extends Fragment {

    private FragmentPatientInfoBinding binding;
    private DoctorViewModel doctorViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doctorViewModel = new ViewModelProvider(requireActivity()).get(DoctorViewModel.class);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Patient Information");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentPatientInfoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.medicationEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { TextViewClicked(); }
        });

        return root;
    }

    public void TextViewClicked() {
        binding.medicationViewswitcher.showNext();
    }
}