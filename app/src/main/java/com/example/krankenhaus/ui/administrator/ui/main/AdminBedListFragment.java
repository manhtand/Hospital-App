package com.example.krankenhaus.ui.administrator.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.krankenhaus.R;
import com.example.krankenhaus.databinding.FragmentBedListBinding;
import com.example.krankenhaus.srccode.entities.relations.BedAndPatient;
import com.example.krankenhaus.ui.administrator.AdministratorActivity;

import java.util.List;

public class AdminBedListFragment extends Fragment {
    RecyclerView recyclerView;
    private FragmentBedListBinding binding;
    private AdminBedAdapter bedAdapter;
    private AdminViewModel administratorViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Bed List");

        binding = FragmentBedListBinding.inflate(inflater, container, false);
        administratorViewModel = AdministratorActivity.obtainViewModel(getActivity());
        View root = binding.getRoot();

        recyclerView = binding.bedList;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        bedAdapter = new AdminBedAdapter();
        recyclerView.setAdapter(bedAdapter);

        administratorViewModel.getAllBedAndPatients().observe(getViewLifecycleOwner(), new Observer<List<BedAndPatient>>() {
            @Override
            public void onChanged(List<BedAndPatient> bedAndPatients) {
                bedAdapter.setBedList(bedAndPatients);
            }
        });
        bedAdapter.setOnItemClickListener(new AdminBedAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BedAndPatient bedAndPatient) {
                AdminPatientInfoFragment adminPatientInfoFragment = new AdminPatientInfoFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.setReorderingAllowed(true);
                ft.addToBackStack(null);
                ft.replace(R.id.nav_host_fragment_activity_administrator, adminPatientInfoFragment);
                ft.commit();

                administratorViewModel.setPatient(bedAndPatient.patient);
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