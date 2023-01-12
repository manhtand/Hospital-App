package com.example.krankenhaus.ui.doctor.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.krankenhaus.R;
import com.example.krankenhaus.srccode.dao.PatientDao;
import com.example.krankenhaus.srccode.entities.Visit;

import java.util.ArrayList;
import java.util.List;

public class VisitAdapter extends RecyclerView.Adapter<VisitAdapter.VisitHolder> {
    private List<Visit> visitList;
    PatientDao patientDao;

    VisitAdapter() {
        this.visitList = new ArrayList<>();
    }

    @NonNull
    @Override
    public VisitHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.visit_item, parent, false);
        return new VisitHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VisitHolder holder, int position) {
        if (visitList.size() == 0) {
            return;
        }
        Visit visit = visitList.get(position);


    }

    @Override
    public int getItemCount() {
        return visitList.size();
    }

    public void setVisitList(List<Visit> visitList) {
        this.visitList = visitList;
        notifyDataSetChanged();
    }

    class VisitHolder extends RecyclerView.ViewHolder {
        private TextView textViewBedNumber;
        private TextView textViewName;
        private TextView textViewInsuranceNumber;

        public VisitHolder(View view) {
            super(view);
            textViewBedNumber = view.findViewById(R.id.visitlist_bed_number);
            textViewName = view.findViewById(R.id.visit_patient_name);
            textViewInsuranceNumber = view.findViewById(R.id.visit_patient_insurance_number);
        }
    }
}
