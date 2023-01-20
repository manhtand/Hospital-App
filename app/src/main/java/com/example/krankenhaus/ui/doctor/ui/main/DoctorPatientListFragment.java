package com.example.krankenhaus.ui.doctor.ui.main;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.krankenhaus.R;
import com.example.krankenhaus.databinding.DoctorFragmentPatientListBinding;
import com.example.krankenhaus.srccode.entities.relations.PatientAndBed;
import com.example.krankenhaus.srccode.entities.relations.PatientAndRecord;
import com.example.krankenhaus.srccode.entities.relations.RecordWithAll;

import java.util.List;

public class DoctorPatientListFragment extends Fragment {
    RecyclerView recyclerView;
    private DoctorFragmentPatientListBinding binding;
    private PatientAdapter patientAdapter;
    private DoctorViewModel doctorViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doctorViewModel = new ViewModelProvider(requireActivity()).get(DoctorViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Patient List");

        binding = DoctorFragmentPatientListBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = binding.patientList;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        patientAdapter = new PatientAdapter();
        recyclerView.setAdapter(patientAdapter);

        doctorViewModel.getAllPatientAndBeds().observe(getViewLifecycleOwner(), new Observer<List<PatientAndBed>>() {
            @Override
            public void onChanged(List<PatientAndBed> patientAndBeds) {
                patientAdapter.setPatientAndBedList(patientAndBeds);
            }
        });

        patientAdapter.setOnItemClickListener(new PatientAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(PatientAndBed patientAndBed) {
                doctorViewModel.getRecordWithAllByInsuranceNumber(patientAndBed.patient.getInsuranceNumber()).observe(getViewLifecycleOwner(), new Observer<RecordWithAll>() {
                    @Override
                    public void onChanged(RecordWithAll recordWithAll) {
                        doctorViewModel.setRecordWithAllByInsuranceNumber(recordWithAll);
                    }
                });

                SystemClock.sleep(50);

                DoctorPatientInfoFragment doctorPatientInfoFragment = new DoctorPatientInfoFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.setReorderingAllowed(true);
                ft.addToBackStack(null);
                ft.replace(R.id.nav_host_fragment_activity_doctor, doctorPatientInfoFragment);
                ft.commit();
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
}