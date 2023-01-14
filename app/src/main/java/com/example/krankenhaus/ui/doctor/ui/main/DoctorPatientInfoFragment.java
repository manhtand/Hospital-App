package com.example.krankenhaus.ui.doctor.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.krankenhaus.databinding.DoctorFragmentPatientInfoBinding;
import com.example.krankenhaus.srccode.entities.Patient;

import java.time.format.DateTimeFormatter;

public class DoctorPatientInfoFragment extends Fragment {
    private Patient patient;
    private DoctorFragmentPatientInfoBinding binding;
    private DoctorViewModel doctorViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doctorViewModel = new ViewModelProvider(requireActivity()).get(DoctorViewModel.class);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Patient Information");

        doctorViewModel.getPatient().observe(getActivity(), new Observer<Patient>() {
            @Override
            public void onChanged(Patient s) {
                patient = s;
            }
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DoctorFragmentPatientInfoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.medicationEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { TextViewClicked(); }
        });

        setPatientData(patient);

        return root;
    }

    public void TextViewClicked() {
        binding.medicationViewswitcher.showNext();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void setPatientData(Patient patient) {
        binding.patientInfoName.setText(patient.getName());
        binding.dateOfBirth.setText(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(patient.getDateOfBirth()));
        binding.address.setText(patient.getAddress());
        binding.zipCode.setText(patient.getZipCode());
        binding.placeOfResidence.setText(patient.getPlaceOfResidence());
        binding.insuranceNumber.setText(patient.getInsuranceNumber());
        binding.healthInsuranceCompany.setText(patient.getHealthInsuranceCompany());
        binding.admissionDate.setText(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(patient.getAdmissionDate()));

        if (patient.isDischarged()) {
            binding.yesRadioButton.setChecked(true);
            binding.noRadioButton.setChecked(false);
        }
        else {
            binding.yesRadioButton.setChecked(false);
            binding.noRadioButton.setChecked(true);
        }
    }
}