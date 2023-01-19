package com.example.krankenhaus.ui.service.labor.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.krankenhaus.databinding.FragmentLaborResultBloodTestBinding;
import com.example.krankenhaus.srccode.entities.BloodTest;
import com.example.krankenhaus.srccode.entities.relations.BloodTestAndRecord;
import com.example.krankenhaus.ui.doctor.ui.main.DoctorViewModel;
import com.example.krankenhaus.ui.service.labor.LaborActivity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class LaborResultBloodTestFragment extends Fragment {
    private LaborViewModel laborViewModel;
    private BloodTestAndRecord bloodTestAndRecord;
    private FragmentLaborResultBloodTestBinding binding;
    private BloodTest newBloodTest;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        laborViewModel = LaborActivity.obtainViewModel(getActivity());

        laborViewModel.getBloodTestAndRecord().observe(getActivity(), new Observer<BloodTestAndRecord>() {
            @Override
            public void onChanged(BloodTestAndRecord input) {
                bloodTestAndRecord = input;
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLaborResultBloodTestBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Blood Test Result");

        binding.laborResultBloodtestInsertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertBloodTest();
            }
        });

        binding.laborResultBloodtestSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveBloodTest();
            }
        });
        return root;
    }

    private void insertBloodTest() {
        Random random = new Random();
        int index = random.nextInt(laborViewModel.getBloodTestSource().size());
        newBloodTest = laborViewModel.getBloodTestSource().get(index);
        newBloodTest.setExecutionTimestamp(LocalDateTime.now());

        binding.leukocytesPerNanoliter.setText("Leukocytes Per NanoLiter: " + Double.toString(newBloodTest.getLeukocytesPerNanoLiter()));
        binding.lymphocytesLeukocytesRatio.setText("Lymphocytes / Leukocytes: " + Double.toString(newBloodTest.getLymphocytesLeukocytesRatio()));
        binding.lymphocytesInHundredPerNanoLiter.setText("Lymphocytes In Hundred Per Nanoliter: " + Double.toString(newBloodTest.getLymphocytesInHundredPerNanoLiter()));
    }

    private void saveBloodTest() {
        BloodTest bloodTest = bloodTestAndRecord.bloodTest;
        newBloodTest.setId(bloodTest.getId());
        newBloodTest.setCreationTimestamp(bloodTest.getCreationTimestamp());
        laborViewModel.updateBloodTest(newBloodTest);
    }
}
