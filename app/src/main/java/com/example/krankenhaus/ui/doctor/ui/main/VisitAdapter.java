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
import com.example.krankenhaus.srccode.entities.relations.RecordAndVisitAndPatient;

import java.util.ArrayList;
import java.util.List;

public class VisitAdapter extends RecyclerView.Adapter<VisitAdapter.VisitHolder> {
    private List<RecordAndVisitAndPatient> recordAndVisitAndPatientList;
    PatientDao patientDao;

    VisitAdapter() {
        this.recordAndVisitAndPatientList = new ArrayList<>();
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
        if (recordAndVisitAndPatientList.size() == 0) {
            return;
        }
        RecordAndVisitAndPatient recordAndVisitAndPatient = recordAndVisitAndPatientList.get(position);
        holder.textViewName.setText(recordAndVisitAndPatient.patient.getName());
        holder.textViewInsuranceNumber.setText(recordAndVisitAndPatient.patient.getInsuranceNumber());
        holder.textViewBedNumber.setText(recordAndVisitAndPatient.patient.getBedNumber());
    }

    @Override
    public int getItemCount() {
        return recordAndVisitAndPatientList.size();
    }

    public void setRecordAndVisitAndPatientList(List<RecordAndVisitAndPatient> recordAndVisitAndPatientList) {
        this.recordAndVisitAndPatientList = recordAndVisitAndPatientList;
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
