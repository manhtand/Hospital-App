package com.example.krankenhaus.ui.doctor.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.krankenhaus.databinding.DoctorFragmentPatientInfoBinding;
import com.example.krankenhaus.srccode.entities.BloodTest;
import com.example.krankenhaus.srccode.entities.MRI;
import com.example.krankenhaus.srccode.entities.Patient;
import com.example.krankenhaus.srccode.entities.Record;
import com.example.krankenhaus.srccode.entities.relations.BloodTestAndRecord;
import com.example.krankenhaus.srccode.entities.relations.MRIAndRecord;
import com.example.krankenhaus.srccode.entities.relations.PatientAndRecord;
import com.example.krankenhaus.srccode.entities.relations.RecordAndPatient;
import com.example.krankenhaus.srccode.entities.relations.RecordWithAll;
import com.example.krankenhaus.srccode.repository.BloodTestRepository;
import com.example.krankenhaus.R;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DoctorPatientInfoFragment extends Fragment {
    private RecordWithAll recordWithAll;
    private DoctorFragmentPatientInfoBinding binding;
    private DoctorViewModel doctorViewModel;
    private Patient patient;
    private Record record;
    private MRI mri;
    private BloodTest bloodTest;
    private List<MRI> mriDoneList = new ArrayList<>();
    private List<BloodTest> bloodTestDoneList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doctorViewModel = new ViewModelProvider(requireActivity()).get(DoctorViewModel.class);

        doctorViewModel.getRecordWithAllByInsuranceNumber().observe(getActivity(), new Observer<RecordWithAll>() {
            @Override
            public void onChanged(RecordWithAll input) {
                recordWithAll = input;
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
                recordWithAll.record.setMedication(binding.medicationEdittext.getText().toString());
                binding.medicationTextview.setText(recordWithAll.record.getMedication());
            }
        });

        setPatientAndRecord();
        setExamination();
        setPatientData(patient);
        setDischarged();

        setUpButton();

        return root;
    }

    private void setUpButton(){
        if (mri == null || bloodTest == null) {
            binding.assignmentRequestButton.setText("Request");
            binding.assignmentRequestButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    doctorViewModel.insertExamination(new MRI(recordWithAll.record.getRecordId(), false, LocalDateTime.of(0001,01,1,0,0), new byte[0]), new BloodTest(recordWithAll.record.getRecordId(), false, LocalDateTime.of(0001,01,1,0,0), -1, -1, -1));
                    showNoti();
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
    }

    private void showNoti(){
        Toast.makeText(getContext(), "Tests requested", Toast.LENGTH_LONG).show();
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
        patient = recordWithAll.patient;
        record = recordWithAll.record;
    }

    private void setExamination() {
        setMRI();
        setBloodTest();
    }

    private void setMRI(){
        if(recordWithAll.mri==null){
            return;
        }
        Comparator<MRI> comparatorDesc = (mri1, mri2) -> mri2.getExecutionTimestamp()
                .compareTo(mri1.getExecutionTimestamp());

        for(MRI tmp : recordWithAll.mri){
            if(tmp.getProcessingState()){
                mriDoneList.add(tmp);
            }
        }
        Collections.sort(mriDoneList, comparatorDesc);
        if(mriDoneList.size()==0){
            return;
        }
        mri = mriDoneList.get(0);
        doctorViewModel.setMRIResult(mri);
    }

    private void setBloodTest(){
        if(recordWithAll.bloodTest==null){
            return;
        }
        Comparator<BloodTest> comparatorDesc = (bl1, bl2) -> bl2.getExecutionTimestamp()
                .compareTo(bl1.getExecutionTimestamp());

        for(BloodTest tmp : recordWithAll.bloodTest){
            if(tmp.getProcessingState()){
                bloodTestDoneList.add(tmp);
            }
        }
        Collections.sort(bloodTestDoneList, comparatorDesc);
        if(bloodTestDoneList.size()==0){
            return;
        }
        bloodTest = bloodTestDoneList.get(0);
        doctorViewModel.setBloodTestResult(bloodTest);
    }
}