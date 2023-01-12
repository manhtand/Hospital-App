package com.example.krankenhaus.ui.administrator.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.krankenhaus.R;
import com.example.krankenhaus.databinding.FragmentBedListBinding;
import com.example.krankenhaus.srccode.entities.Bed;
import com.example.krankenhaus.ui.administrator.AdministratorActivity;
import com.example.krankenhaus.ui.administrator.ui.main.AdministratorViewModel;

import java.util.List;

public class BedListFragment extends Fragment {
    RecyclerView recyclerView;
    private FragmentBedListBinding binding;
    private BedAdapter bedAdapter;
    private AdministratorViewModel administratorViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Bed List");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentBedListBinding.inflate(inflater, container, false);
        administratorViewModel = AdministratorActivity.obtainViewModel(getActivity());
        View root = binding.getRoot();

        recyclerView = binding.bedList;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        bedAdapter = new BedAdapter();
        recyclerView.setAdapter(bedAdapter);

        administratorViewModel.getAllBeds().observe(getViewLifecycleOwner(), new Observer<List<Bed>>() {
            @Override
            public void onChanged(List<Bed> beds) {
                if (beds == null) {
                    return;
                }
                bedAdapter.setBedList(beds);
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