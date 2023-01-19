package com.example.krankenhaus.ui.service.labor.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.krankenhaus.R;
import com.example.krankenhaus.databinding.FragmentMriListBinding;
import com.example.krankenhaus.srccode.entities.relations.MRIAndRecord;
import com.example.krankenhaus.ui.service.labor.LaborActivity;

import java.util.List;

public class MriListFragment extends Fragment {
    RecyclerView recyclerView;
    private FragmentMriListBinding binding;
    private MriAdapter mriAdapter;
    private LaborViewModel laborViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("MRT List");

        binding = FragmentMriListBinding.inflate(inflater, container, false);
        laborViewModel = LaborActivity.obtainViewModel(getActivity());
        View root = binding.getRoot();

        recyclerView = binding.laborMrtList;

        mriAdapter = new MriAdapter();
        recyclerView.setAdapter(mriAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        laborViewModel.getAllNewMRIAndRecord().observe(getViewLifecycleOwner(), new Observer<List<MRIAndRecord>>() {
            @Override
            public void onChanged(List<MRIAndRecord> mriAndRecords) {
                mriAdapter.setMRIAndRecordList(mriAndRecords);
            }
        });

        laborViewModel.getAllDoneMRIAndRecord().observe(getViewLifecycleOwner(), new Observer<List<MRIAndRecord>>() {
            @Override
            public void onChanged(List<MRIAndRecord> mriAndRecords) {
                mriAndRecords.size();
            }
        });

        mriAdapter.setOnItemClickListener(new MriAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(MRIAndRecord mriAndRecord) {
                laborViewModel.setMriAndRecord(mriAndRecord);

                SystemClock.sleep(50);
                LaborResultMRIFragment laborResultMRIFragment = new LaborResultMRIFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.setReorderingAllowed(true);
                ft.addToBackStack(null);
                ft.replace(R.id.nav_host_fragment_activity_labor, laborResultMRIFragment);
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