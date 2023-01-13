package com.example.krankenhaus.ui.doctor.ui.main;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import com.example.krankenhaus.R;
import com.example.krankenhaus.srccode.entities.Patient;
import com.example.krankenhaus.srccode.entities.relations.PatientAndBed;
import com.example.krankenhaus.ui.doctor.DoctorActivity;

import java.util.ArrayList;
import java.util.List;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.PatientAndBedHolder> {
    private List<PatientAndBed> patientAndBedList;
    private OnItemClickListener listener;

    public PatientAdapter() {
        this.patientAndBedList = new ArrayList<>();
    }

    @NonNull
    @Override
    public PatientAndBedHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.patient_item, parent, false);
        return new PatientAndBedHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientAndBedHolder holder, int position) {
        if (patientAndBedList.size() == 0) {
            return;
        }

        PatientAndBed patientAndBed = patientAndBedList.get(position);
        String bedNumber = "Bed number: " + String.valueOf(patientAndBed.bed.getNumber());
        String patientName = patientAndBed.patient.getName();
        holder.textViewName.setText(patientName);
        holder.textViewBedNumber.setText(bedNumber);
    }

    @Override
    public int getItemCount() {
        return patientAndBedList.size();
    }

    public void setPatientAndBedList(List<PatientAndBed> patientAndBedList) {
        this.patientAndBedList = patientAndBedList;
        notifyDataSetChanged();
    }

    class PatientAndBedHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;
        private TextView textViewBedNumber;

        public PatientAndBedHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.patient_adapter_patient_name);
            textViewBedNumber = itemView.findViewById(R.id.patient_adapter_bed_number);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(patientAndBedList.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(PatientAndBed patientAndBed);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
