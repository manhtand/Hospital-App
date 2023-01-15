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
import com.example.krankenhaus.srccode.entities.relations.PatientAndBed;
import com.example.krankenhaus.srccode.entities.relations.RecordAndVisitAndPatient;

import java.time.format.DateTimeFormatter;

public class DoctorPatientInfoFragment extends Fragment {
    private RecordAndVisitAndPatient recordAndVisitAndPatient;
    private String insuranceNumber;
    private DoctorFragmentPatientInfoBinding binding;
    private DoctorViewModel doctorViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doctorViewModel = new ViewModelProvider(requireActivity()).get(DoctorViewModel.class);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Patient Information");

        doctorViewModel.getInsuranceNumber().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                insuranceNumber = s;
            }
        });

        doctorViewModel.getRecordAndVisitAndPatientByInsuranceNumber(insuranceNumber).observe(getActivity(), new Observer<RecordAndVisitAndPatient>() {
            @Override
            public void onChanged(RecordAndVisitAndPatient input) {
                recordAndVisitAndPatient = input;
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
            public void onClick(View view) {
                changeViewSwitcher();
                recordAndVisitAndPatient.record.setMedication(binding.medicationEdittext.getText().toString());
                binding.medicationTextview.setText(recordAndVisitAndPatient.record.getMedication());
            }
        });

        setPatientData(recordAndVisitAndPatient.patient);
        setDischarged();

        return root;
    }

    private void changeViewSwitcher() {
        binding.medicationViewswitcher.showNext();
        if (binding.medicationEdittext.isShown()) {
            binding.medicationEditButton.setText("Save");
        }
        else {
            binding.medicationEditButton.setText("Edit");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void setPatientData(Patient patient) {
        binding.patientInfoName.setText(patient.getName());
        binding.patientInfoRecordId.setText(recordAndVisitAndPatient.record.getRecordId());
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

    private void setDischarged() {
        binding.yesRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recordAndVisitAndPatient.patient.setDischarged(true);
            }
        });
        binding.noRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recordAndVisitAndPatient.patient.setDischarged(false);
            }
        });
    }
}