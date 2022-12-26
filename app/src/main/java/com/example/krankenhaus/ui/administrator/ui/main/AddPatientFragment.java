package com.example.krankenhaus.ui.administrator.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.krankenhaus.R;
import com.example.krankenhaus.databinding.FragmentAdministratorDashboardBinding;
import com.example.krankenhaus.ui.administrator.AdministratorActivity;
import com.example.krankenhaus.databinding.FragmentAddPatientBinding;

import java.time.LocalDate;

public class AddPatientFragment extends Fragment {
    AdministratorViewModel administratorViewModel;
    private FragmentAddPatientBinding binding;

    int year;
    int month;
    int day;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        administratorViewModel = new ViewModelProvider(requireActivity()).get(AdministratorViewModel.class);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Add Patient");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddPatientBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.npYear.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldVal, int newVal) {
                year = newVal;
            }
        });
        binding.npYear.setMaxValue(LocalDate.now().getYear());
        binding.npYear.setMinValue(LocalDate.now().getYear() - 150);

        binding.npMonth.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldVal, int newVal) {
                month = newVal;
            }
        });
        binding.npMonth.setMaxValue(12);
        binding.npMonth.setMinValue(1);

        binding.npDay.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
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
            binding.npDay.setMaxValue(31);
        }
        else if (month == 2) {
            if (year % 4 == 0) {
                binding.npDay.setMaxValue(29);
            }
            else {
                binding.npDay.setMaxValue(28);
            }
        }
        else {
            binding.npDay.setMaxValue(30);
        }

        binding.createPatientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePatient();
                returnToPatientList();
            }
        });


        return root;
    }

    private void savePatient() {
        String name = binding.etName.getText().toString();
        LocalDate dob = LocalDate.of(year, month, day);
        String address = binding.etAddress.getText().toString();
        String zipcode = binding.etZipcode.getText().toString();
        String placeOfResidence = binding.etPlaceofresidence.getText().toString();
        String insuranceNumber = binding.etInsurancenumber.getText().toString();
        String insuranceCompany = binding.etInsurancecompany.getText().toString();

        if (name.trim().isEmpty() ||
                address.trim().isEmpty() ||
                zipcode.trim().isEmpty() ||
                placeOfResidence.trim().isEmpty() ||
                insuranceNumber.trim().isEmpty() ||
                insuranceCompany.trim().isEmpty()) {
            Toast.makeText(getActivity(), "Please insert all informations", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    public void returnToPatientList() {
        AdminPatientListFragment adminPatientListFragment = new AdminPatientListFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.nav_host_fragment_activity_administrator, adminPatientListFragment);
        ft.commit();
    }
}