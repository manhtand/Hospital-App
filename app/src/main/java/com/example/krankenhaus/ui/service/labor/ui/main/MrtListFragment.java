package com.example.krankenhaus.ui.service.labor.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.krankenhaus.R;
import com.example.krankenhaus.databinding.FragmentMrtListBinding;

public class MrtListFragment extends Fragment {
    RecyclerView recyclerView;
    private FragmentMrtListBinding binding;
    private MrtAdapter mrtAdapter;
    private LaborViewModel laborViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("MRT List");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentMrtListBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = binding.laborMrtList;

        mrtAdapter = new MrtAdapter();
        recyclerView.setAdapter(mrtAdapter);

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