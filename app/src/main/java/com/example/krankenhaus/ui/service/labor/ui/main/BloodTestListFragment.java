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
import com.example.krankenhaus.databinding.FragmentBloodTestListBinding;
import com.example.krankenhaus.srccode.entities.relations.BloodTestAndRecord;
import com.example.krankenhaus.ui.service.labor.LaborActivity;

import java.util.List;

public class BloodTestListFragment extends Fragment {
    RecyclerView recyclerView;
    private FragmentBloodTestListBinding binding;
    private BloodTestAdapter bloodTestAdapter;
    private LaborViewModel laborViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Blood Test List");

        binding = FragmentBloodTestListBinding.inflate(inflater, container, false);
        laborViewModel = LaborActivity.obtainViewModel(getActivity());
        View root = binding.getRoot();

        recyclerView = binding.laborBloodTestList;

        bloodTestAdapter = new BloodTestAdapter();
        recyclerView.setAdapter(bloodTestAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        laborViewModel.getAllNewBloodTestAndRecord().observe(getViewLifecycleOwner(), new Observer<List<BloodTestAndRecord>>() {
            @Override
            public void onChanged(List<BloodTestAndRecord> bloodTestAndRecords) {
                bloodTestAdapter.setBloodTestAndRecordList(bloodTestAndRecords);
            }
        });

        bloodTestAdapter.setOnItemClickListener(new BloodTestAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BloodTestAndRecord bloodTestAndRecord) {
                laborViewModel.setBloodTestAndRecord(bloodTestAndRecord);

                SystemClock.sleep(50);
                LaborResultBloodTestFragment laborResultBloodTestFragment = new LaborResultBloodTestFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.setReorderingAllowed(true);
                ft.addToBackStack(null);
                ft.replace(R.id.nav_host_fragment_activity_labor, laborResultBloodTestFragment);
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