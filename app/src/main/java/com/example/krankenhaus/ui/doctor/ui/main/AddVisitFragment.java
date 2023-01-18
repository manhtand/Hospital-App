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

import com.example.krankenhaus.R;
import com.example.krankenhaus.databinding.FragmentAddVisitBinding;
import com.example.krankenhaus.srccode.entities.Visit;
import com.example.krankenhaus.srccode.entities.relations.RecordAndVisitAndPatient;

public class AddVisitFragment extends Fragment {
    DoctorViewModel doctorViewModel;
    private FragmentAddVisitBinding binding;

    private String insuranceNumber;
    private String description;
    private RecordAndVisitAndPatient recordAndVisitAndPatient;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Add Visit");

        binding = FragmentAddVisitBinding.inflate(inflater, container, false);
        doctorViewModel = new ViewModelProvider(requireActivity()).get(DoctorViewModel.class);
        View root = binding.getRoot();

        binding.createVisitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insuranceNumber = binding.etVisitInsuranceNumber.getText().toString();
                description = binding.etVisitDescription.getText().toString();

                doctorViewModel.getRecordAndVisitAndPatientByInsuranceNumber(insuranceNumber).observe(getViewLifecycleOwner(), new Observer<RecordAndVisitAndPatient>() {
                    @Override
                    public void onChanged(RecordAndVisitAndPatient input) {
                        recordAndVisitAndPatient = input;

                        checkInsuranceNumber();
                        returnToVisitList();
                    }
                });
            }
        });

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void checkInsuranceNumber() {
        if (recordAndVisitAndPatient == null) {
            Toast toast = Toast.makeText(getContext(), "Invalid Record ID", Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            doctorViewModel.insertVisit(new Visit(recordAndVisitAndPatient.record.getRecordId(), description));
            Toast toast = Toast.makeText(getContext(), "Add Succeed", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void returnToVisitList() {
        if (getFragmentManager().getBackStackEntryCount() != 0) {
            getFragmentManager().popBackStack();
        }
    }
}