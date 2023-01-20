package com.example.krankenhaus.ui.administrator.ui.main;

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
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.krankenhaus.R;
import com.example.krankenhaus.databinding.FragmentEditPatientBinding;
import com.example.krankenhaus.srccode.entities.Bed;
import com.example.krankenhaus.srccode.entities.Patient;
import com.example.krankenhaus.srccode.entities.Record;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AdminEditPatientFragment extends Fragment {
    private FragmentEditPatientBinding binding;
    private AdminViewModel adminViewModel;

    private Patient patient;
    private List<Bed> allFreeBeds;

    private int year;
    private int month;
    private int day;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adminViewModel = new ViewModelProvider(requireActivity()).get(AdminViewModel.class);

        adminViewModel.getPatient().observe(getActivity(), new Observer<Patient>() {
            @Override
            public void onChanged(Patient p) {
                patient = p;
            }
        });
        adminViewModel.getFreeBeds().observe(getActivity(), new Observer<List<Bed>>() {
            @Override
            public void onChanged(List<Bed> beds) {
                allFreeBeds = beds;
            }
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Patient Information");

        binding = FragmentEditPatientBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        setCurrentPatientInfo();
        setUpDateOfBirth();

        binding.editPatientInfoSaveInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePatient();
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

    private void setCurrentPatientInfo(){
        binding.adminEditPatientInfoName.setText(patient.getName());
        binding.adminEditPatientInfoBednumber.setText(String.valueOf(patient.getBedNumber()));
        year = patient.getDateOfBirth().getYear();
        month = patient.getDateOfBirth().getMonthValue();
        day = patient.getDateOfBirth().getDayOfMonth();

        binding.adminEditPatientInfoYear.setMaxValue(LocalDate.now().getYear());
        binding.adminEditPatientInfoYear.setMinValue(LocalDate.now().getYear() - 150);
        binding.adminEditPatientInfoYear.setValue(year);

        binding.adminEditPatientInfoMonth.setMaxValue(12);
        binding.adminEditPatientInfoMonth.setMinValue(1);
        binding.adminEditPatientInfoMonth.setValue(month);

        binding.adminEditPatientInfoDay.setMaxValue(31);
        binding.adminEditPatientInfoDay.setMinValue(1);
        binding.adminEditPatientInfoDay.setValue(day);

        binding.adminEditPatientInfoAddress.setText(patient.getAddress());
        binding.adminEditPatientInfoZipcode.setText(patient.getZipCode());
        binding.adminEditPatientInfoPlaceofresidence.setText(patient.getPlaceOfResidence());
        binding.adminEditPatientInfoInsurancenumber.setText(patient.getInsuranceNumber());
        binding.adminEditPatientInfoInsurancecompany.setText(patient.getHealthInsuranceCompany());
    }

    private void updatePatient() {

        String name = binding.adminEditPatientInfoName.getText().toString();
        String bedNumber = binding.adminEditPatientInfoBednumber.getText().toString();
        LocalDate dob = LocalDate.of(year, month, day);
        String address = binding.adminEditPatientInfoAddress.getText().toString();
        String zipcode = binding.adminEditPatientInfoZipcode.getText().toString();
        String placeOfResidence = binding.adminEditPatientInfoPlaceofresidence.getText().toString();
        String insuranceNumber = binding.adminEditPatientInfoInsurancenumber.getText().toString();
        String insuranceCompany = binding.adminEditPatientInfoInsurancecompany.getText().toString();

        if(checkIfBedFree(Integer.parseInt(bedNumber))){
            Toast.makeText(getActivity(), "Bed not available", Toast.LENGTH_SHORT).show();
            return;
        }

        if (name.trim().isEmpty() ||
                address.trim().isEmpty() ||
                zipcode.trim().isEmpty() ||
                placeOfResidence.trim().isEmpty() ||
                insuranceNumber.trim().isEmpty() ||
                insuranceCompany.trim().isEmpty()) {
            Toast.makeText(getActivity(), "Please insert all informations", Toast.LENGTH_SHORT).show();
            return;
        }
        patient.setName(name);
        patient.setBedNumber(Integer.parseInt(bedNumber));
        patient.setDateOfBirth(dob);
        patient.setAddress(address);
        patient.setPlaceOfResidence(placeOfResidence);
        patient.setInsuranceNumber(insuranceNumber);
        patient.setHealthInsuranceCompany(insuranceCompany);
        adminViewModel.updatePatient(patient);

        Toast.makeText(getContext(), "Patient Edited", Toast.LENGTH_LONG).show();
    }

    public void returnToAdminPatientList() {
        if (getFragmentManager().getBackStackEntryCount() != 0) {
            getFragmentManager().popBackStack();
            getFragmentManager().popBackStack();
            getFragmentManager().popBackStack();
        }
        AdminPatientListFragment adminPatientListFragment = new AdminPatientListFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.nav_host_fragment_activity_administrator, adminPatientListFragment);
        ft.commit();
    }

    private boolean checkIfBedFree(int bedNumber){
        for(Bed b : allFreeBeds){
            if(b.getNumber() == bedNumber){
                return true;
            }
        }
        return false;
    }

    private void setUpDateOfBirth(){
        binding.adminEditPatientInfoYear.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldVal, int newVal) {
                year = newVal;
            }
        });
        binding.adminEditPatientInfoYear.setMaxValue(LocalDate.now().getYear());
        binding.adminEditPatientInfoYear.setMinValue(LocalDate.now().getYear() - 150);

        binding.adminEditPatientInfoMonth.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldVal, int newVal) {
                month = newVal;
            }
        });
        binding.adminEditPatientInfoMonth.setMaxValue(12);
        binding.adminEditPatientInfoMonth.setMinValue(1);

        binding.adminEditPatientInfoDay.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldVal, int newVal) {
                day = newVal;
            }
        });
        if (month == 1 ||
                month == 3 ||
                month == 5 ||
                month == 7 ||
                month == 8 ||
                month == 10 ||
                month == 12) {
            binding.adminEditPatientInfoDay.setMaxValue(31);
        }
        else if (month == 2) {
            if (year % 4 == 0) {
                binding.adminEditPatientInfoDay.setMaxValue(29);
            }
            else {
                binding.adminEditPatientInfoDay.setMaxValue(28);
            }
        }
        else {
            binding.adminEditPatientInfoDay.setMaxValue(30);
        }
        binding.adminEditPatientInfoDay.setMinValue(1);
    }
}