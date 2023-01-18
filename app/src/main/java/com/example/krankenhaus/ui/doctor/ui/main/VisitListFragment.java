package com.example.krankenhaus.ui.doctor.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.krankenhaus.R;
import com.example.krankenhaus.databinding.FragmentVisitListBinding;
import com.example.krankenhaus.srccode.entities.Visit;
import com.example.krankenhaus.srccode.entities.relations.RecordAndPatient;
import com.example.krankenhaus.srccode.entities.relations.RecordAndVisitAndPatient;
import com.example.krankenhaus.srccode.entities.relations.VisitAndRecord;

import java.util.List;

public class VisitListFragment extends Fragment {
    RecyclerView recyclerView;
    private FragmentVisitListBinding binding;
    private VisitAdapter visitAdapter;
    private DoctorViewModel doctorViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doctorViewModel = new ViewModelProvider(requireActivity()).get(DoctorViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Visit List");

        binding = FragmentVisitListBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = binding.visitList;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        visitAdapter = new VisitAdapter(getViewLifecycleOwner(), doctorViewModel);
        recyclerView.setAdapter(visitAdapter);

        doctorViewModel.getAllVisitAndRecords().observe(getViewLifecycleOwner(), new Observer<List<VisitAndRecord>>() {
            @Override
            public void onChanged(List<VisitAndRecord> visitAndRecordList) {
                visitAdapter.setVisitAndRecordList(visitAndRecordList);
            }
        });

        // Swipe to delete visit
        /*new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                doctorViewModel.delete(visitAdapter.getBedAt(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(recyclerView);*/

        binding.addVisitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddVisitFragment addVisitFragment = new AddVisitFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.nav_host_fragment_activity_doctor, addVisitFragment);
                ft.addToBackStack(null);
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