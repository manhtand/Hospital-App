package com.example.krankenhaus.ui.doctor.ui.main;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.krankenhaus.databinding.FragmentExaminationShowBinding;

import com.example.krankenhaus.srccode.entities.BloodTest;
import com.example.krankenhaus.srccode.entities.MRI;
import com.example.krankenhaus.srccode.entities.relations.BloodTestAndRecord;
import com.example.krankenhaus.srccode.entities.relations.MRIAndRecord;

public class ExaminationShowFragment extends Fragment {
    private DoctorViewModel doctorViewModel;
    private FragmentExaminationShowBinding binding;
    private MRI mri;
    private BloodTest bloodTest;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doctorViewModel = new ViewModelProvider(requireActivity()).get(DoctorViewModel.class);
        doctorViewModel.getBloodTestResult().observe(getActivity(), new Observer<BloodTest>() {
            @Override
            public void onChanged(BloodTest input) {
                bloodTest = input;
            }
        });
        doctorViewModel.getMRIAndRecordResult().observe(getActivity(), new Observer<MRI>() {
            @Override
            public void onChanged(MRI input) {
                mri = input;
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Patient Examination");

        binding = FragmentExaminationShowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.patientLeukocytesPerNanoliter.setText("Leukocytes Per NanoLiter: " + Double.toString(bloodTest.getLeukocytesPerNanoLiter()));
        binding.patientLymphocytesLeukocytesRatio.setText("Lymphocytes / Leukocytes: " + Double.toString(bloodTest.getLymphocytesLeukocytesRatio()));
        binding.patientLymphocytesInHundredPerNanoLiter.setText("Lymphocytes In Hundred Per Nanoliter" + Double.toString(bloodTest.getLymphocytesInHundredPerNanoLiter()));

        getMRI();

        return root;
    }

    private void getMRI() {
        if(mri.getImage()==null){
            return;
        }
        byte[] blob= mri.getImage();
        Bitmap bmp= BitmapFactory.decodeByteArray(blob,0,blob.length);
        if(bmp == null){
            return;
        }
        Resources restmp = getActivity().getResources();
        Drawable tmp = new BitmapDrawable(restmp, bmp);
        binding.patientMriImage.setImageDrawable(tmp);
    }
}