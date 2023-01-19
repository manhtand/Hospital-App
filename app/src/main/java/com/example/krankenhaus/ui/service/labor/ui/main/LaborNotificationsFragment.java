package com.example.krankenhaus.ui.service.labor.ui.main;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.krankenhaus.R;
import com.example.krankenhaus.databinding.FragmentLaborNotificationsBinding;
import com.example.krankenhaus.srccode.entities.relations.BloodTestAndRecord;
import com.example.krankenhaus.srccode.entities.relations.MRIAndRecord;
import com.example.krankenhaus.ui.service.labor.LaborActivity;

public class LaborNotificationsFragment extends Fragment {
    RecyclerView recyclerView;
    private FragmentLaborNotificationsBinding binding;
    private LaborViewModel laborViewModel;
    private LaborExaminationAdapter laborExaminationAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        laborViewModel = LaborActivity.obtainViewModel(getActivity());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Notifications");

        binding = FragmentLaborNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = binding.laborExaminationList;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        laborExaminationAdapter = new LaborExaminationAdapter(laborViewModel,getViewLifecycleOwner());
        recyclerView.setAdapter(laborExaminationAdapter);

        laborExaminationAdapter.setOnItemClickListener(new LaborExaminationAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Object object) {
                if (object instanceof MRIAndRecord) {
                    laborViewModel.setMriAndRecord(((MRIAndRecord) object));

                    SystemClock.sleep(50);

                    LaborResultMRIFragment laborResultMRIFragment = new LaborResultMRIFragment();
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.setReorderingAllowed(true);
                    ft.addToBackStack(null);
                    ft.replace(R.id.nav_host_fragment_activity_doctor, laborResultMRIFragment);
                    ft.commit();
                }

                else if (object instanceof BloodTestAndRecord) {
                    laborViewModel.setBloodTestAndRecord(((BloodTestAndRecord) object));

                    SystemClock.sleep(50);

                    LaborResultBloodTestFragment laborResultBloodTestFragment = new LaborResultBloodTestFragment();
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.setReorderingAllowed(true);
                    ft.addToBackStack(null);
                    ft.replace(R.id.nav_host_fragment_activity_doctor, laborResultBloodTestFragment);
                    ft.commit();
                }
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}