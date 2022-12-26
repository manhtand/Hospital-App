package com.example.krankenhaus.ui.administrator.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.krankenhaus.R;
import com.example.krankenhaus.databinding.FragmentBedListBinding;
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
        administratorViewModel = new ViewModelProvider(requireActivity()).get(AdministratorViewModel.class);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Bed List");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentBedListBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = binding.bedList;

        bedAdapter = new BedAdapter();
        recyclerView.setAdapter(bedAdapter);

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