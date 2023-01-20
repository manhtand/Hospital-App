package com.example.krankenhaus.ui.administrator.ui.main;

import android.os.Bundle;

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

import com.example.krankenhaus.srccode.entities.Bed;
import com.example.krankenhaus.srccode.entities.Patient;

import com.example.krankenhaus.R;
import com.example.krankenhaus.databinding.FragmentAddPatientBinding;
import com.example.krankenhaus.srccode.entities.Record;

import java.time.LocalDate;

public class AdminAddPatientFragment extends Fragment {
    AdminViewModel adminViewModel;
    private FragmentAddPatientBinding binding;

    Bed freeBed;
    private int year;
    private int month;
    private int day;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Add Patient");

        binding = FragmentAddPatientBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        adminViewModel = new ViewModelProvider(requireActivity()).get(AdminViewModel.class);

        adminViewModel.getNextFreeBed().observe(getViewLifecycleOwner(), new Observer<Bed>() {
            @Override
            public void onChanged(Bed bed) {
                freeBed = bed;
            }
        });

        setUpDateOfBirth();

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
        if (freeBed == null) {
            Toast.makeText(getActivity(), "No bed available!", Toast.LENGTH_SHORT).show();
            return;
        }

        String name = binding.etName.getText().toString();
        int bedNumber = freeBed.getNumber();
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
        adminViewModel.insertPatient(new Patient(insuranceNumber, bedNumber, name, dob, address, placeOfResidence, zipcode, insuranceCompany, false));
        adminViewModel.insertRecord(new Record(insuranceNumber));

        Toast.makeText(getContext(), "Patient Added", Toast.LENGTH_LONG).show();
    }

    public void returnToPatientList() {
        if (getFragmentManager().getBackStackEntryCount() != 0) {
            getFragmentManager().popBackStack();
            getFragmentManager().popBackStack();
        }
        AdminPatientListFragment adminPatientListFragment = new AdminPatientListFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.nav_host_fragment_activity_administrator, adminPatientListFragment);
        ft.commit();
    }

    private void setUpDateOfBirth(){
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
        binding.npDay.setMinValue(1);
    }
}