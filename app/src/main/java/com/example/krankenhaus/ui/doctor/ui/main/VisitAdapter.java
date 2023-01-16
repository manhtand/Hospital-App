package com.example.krankenhaus.ui.doctor.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.krankenhaus.R;
import com.example.krankenhaus.srccode.dao.PatientDao;
import com.example.krankenhaus.srccode.entities.Patient;
import com.example.krankenhaus.srccode.entities.Visit;
import com.example.krankenhaus.srccode.entities.relations.RecordAndPatient;
import com.example.krankenhaus.srccode.entities.relations.RecordAndVisitAndPatient;
import com.example.krankenhaus.srccode.entities.relations.VisitAndRecord;
import com.example.krankenhaus.srccode.repository.RecordRepository;

import java.util.ArrayList;
import java.util.List;

public class VisitAdapter extends RecyclerView.Adapter<VisitAdapter.VisitHolder> {
    private List<VisitAndRecord> visitAndRecordList;
    PatientDao patientDao;
    RecordRepository recordRepository;

    VisitAdapter() {
        this.visitAndRecordList = new ArrayList<>();
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
        if (visitAndRecordList.size() == 0) {
            return;
        }
        VisitAndRecord visitAndRecord = visitAndRecordList.get(position);
        RecordAndPatient recordAndPatient = recordRepository.getRecordAndPatientByRecordID(visitAndRecord.record.getRecordId()).getValue();

        holder.textViewBedNumber.setText(recordAndPatient.patient.getBedNumber());
        holder.textViewName.setText(recordAndPatient.patient.getName());
        holder.textViewInsuranceNumber.setText(recordAndPatient.patient.getInsuranceNumber());
    }

    @Override
    public int getItemCount() {
        return visitAndRecordList.size();
    }

    public void setVisitAndRecordList(List<VisitAndRecord> visitAndRecordList) {
        this.visitAndRecordList = visitAndRecordList;
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
