package com.example.krankenhaus.ui.doctor.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.krankenhaus.databinding.DoctorFragmentPatientInfoBinding;
import com.example.krankenhaus.srccode.entities.BloodTest;
import com.example.krankenhaus.srccode.entities.MRI;
import com.example.krankenhaus.srccode.entities.Patient;
import com.example.krankenhaus.srccode.entities.Record;
import com.example.krankenhaus.srccode.entities.relations.PatientAndRecord;
import com.example.krankenhaus.srccode.entities.relations.RecordAndPatient;
import com.example.krankenhaus.srccode.repository.BloodTestRepository;
import com.example.krankenhaus.R;

import java.time.format.DateTimeFormatter;

public class DoctorPatientInfoFragment extends Fragment {
    private PatientAndRecord patientAndRecord;
    private RecordAndPatient recordAndPatient;
    private DoctorFragmentPatientInfoBinding binding;
    private DoctorViewModel doctorViewModel;
    private Patient patient;
    private Record record;
    private MRI mri;
    private BloodTest bloodTest;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doctorViewModel = new ViewModelProvider(requireActivity()).get(DoctorViewModel.class);

        doctorViewModel.getPatientAndRecord().observe(getActivity(), new Observer<PatientAndRecord>() {
            @Override
            public void onChanged(PatientAndRecord input) {
                patientAndRecord = input;
            }
        });

        doctorViewModel.getRecordAndPatient().observe(getActivity(), new Observer<RecordAndPatient>() {
            @Override
            public void onChanged(RecordAndPatient input) {
                recordAndPatient = input;
            }
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Patient Information");

        binding = DoctorFragmentPatientInfoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.medicationEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeViewSwitcher();
                patientAndRecord.record.setMedication(binding.medicationEdittext.getText().toString());
                binding.medicationTextview.setText(patientAndRecord.record.getMedication());
            }
        });

        setPatientAndRecord();
        setExamination();
        setPatientData(patient);
        setDischarged();

        if (mri == null || bloodTest == null) {
            binding.assignmentRequestButton.setText("Request");
            binding.assignmentRequestButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    doctorViewModel.insertExamination(new MRI(patientAndRecord.record.getRecordId(), new byte[0]), new BloodTest(patientAndRecord.record.getRecordId(), -1, -1, -1));
                }
            });
        }
        else {
            binding.assignmentRequestButton.setText("Show");
            binding.assignmentRequestButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ExaminationShowFragment examinationShowFragment = new ExaminationShowFragment();
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.setReorderingAllowed(true);
                    ft.addToBackStack(null);
                    ft.replace(R.id.nav_host_fragment_activity_doctor, examinationShowFragment);
                    ft.commit();
                }
            });
        }

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
        binding.patientInfoRecordId.setText(Integer.toString(record.getRecordId()));
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
                patient.setDischarged(true);
                doctorViewModel.updatePatient(patient);
            }
        });
        binding.noRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                patient.setDischarged(false);
                doctorViewModel.updatePatient(patient);
            }
        });
    }

    private void setPatientAndRecord() {
        if (recordAndPatient != null && patientAndRecord == null) {
            patient = recordAndPatient.patient;
            record = recordAndPatient.record;
        }
        else if (recordAndPatient == null && patientAndRecord != null) {
            patient = patientAndRecord.patient;
            record = patientAndRecord.record;
        }
        else {
            patient = null;
        }
    }

    private void setExamination() {
        doctorViewModel.getAllMRIByRecordID(record.getRecordId()).observe(getViewLifecycleOwner(), new Observer<MRI>() {
            @Override
            public void onChanged(MRI input) {
                mri = input;
            }
        });

        doctorViewModel.getAllBloodTestByRecordID(record.getRecordId()).observe(getViewLifecycleOwner(), new Observer<BloodTest>() {
            @Override
            public void onChanged(BloodTest input) {
                bloodTest = input;
            }
        });
    }
}