package com.example.krankenhaus.ui.doctor.ui.main;

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
import android.widget.TextView;

import com.example.krankenhaus.R;
import com.example.krankenhaus.databinding.FragmentDoctorNotificationsBinding;
import com.example.krankenhaus.srccode.entities.relations.BloodTestAndRecord;
import com.example.krankenhaus.srccode.entities.relations.MRIAndRecord;
import com.example.krankenhaus.srccode.entities.relations.PatientAndRecord;
import com.example.krankenhaus.srccode.entities.relations.RecordAndPatient;
import com.example.krankenhaus.ui.doctor.DoctorActivity;

import java.util.List;

public class DoctorNotificationsFragment extends Fragment {
    RecyclerView recyclerView;
    private FragmentDoctorNotificationsBinding binding;
    private DoctorViewModel doctorViewModel;
    private ExaminationAdapter examinationAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doctorViewModel = new ViewModelProvider(requireActivity()).get(DoctorViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Notifications");

        binding = FragmentDoctorNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = binding.doctorExaminationList;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        examinationAdapter = new ExaminationAdapter(doctorViewModel, getViewLifecycleOwner());
        recyclerView.setAdapter(examinationAdapter);

        /*doctorViewModel.getAllExaminations().observe(getViewLifecycleOwner(), new Observer<List<Object>>() {
            @Override
            public void onChanged(List<Object> objects) {
                examinationAdapter.setExaminationList(objects);
            }
        });*/

        examinationAdapter.setOnItemClickListener(new ExaminationAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Object object) {
                if (object instanceof MRIAndRecord) {
                    doctorViewModel.getRecordAndPatientByRecordID(((MRIAndRecord) object).record.getRecordId()).observe(getViewLifecycleOwner(), new Observer<RecordAndPatient>() {
                        @Override
                        public void onChanged(RecordAndPatient recordAndPatient) {
                            doctorViewModel.setRecordAndPatient(recordAndPatient);
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

                else if (object instanceof BloodTestAndRecord) {
                    doctorViewModel.getRecordAndPatientByRecordID(((BloodTestAndRecord) object).record.getRecordId()).observe(getViewLifecycleOwner(), new Observer<RecordAndPatient>() {
                        @Override
                        public void onChanged(RecordAndPatient recordAndPatient) {
                            doctorViewModel.setRecordAndPatient(recordAndPatient);
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
            }
        });

        return root;
    }

    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}