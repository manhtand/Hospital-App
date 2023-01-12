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
import com.example.krankenhaus.ui.doctor.DoctorActivity;

import java.util.ArrayList;
import java.util.List;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.PatientHolder> {
    private List<Patient> patientList;
    private OnItemClickListener listener;

    public PatientAdapter() {
        this.patientList = new ArrayList<>();
    }

    @NonNull
    @Override
    public PatientHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.patient_item, parent, false);
        return new PatientHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientHolder holder, int position) {
        if (patientList.size() == 0) {
            return;
        }

        Patient patient = patientList.get(position);
        holder.textViewName.setText(patient.getName());
        holder.setRadioButtonDischarged(patient.isDischarged());
    }

    @Override
    public int getItemCount() {
        return patientList.size();
    }

    public void setPatientList(List<Patient> patientList) {
        this.patientList = patientList;
        notifyDataSetChanged();
    }

    class PatientHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;
        private RadioButton radioButtonDischarged;
        private RadioButton radioButtonNoDischarged;

        public PatientHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.patient_name);
            radioButtonDischarged = itemView.findViewById(R.id.yes_radio_button);
            radioButtonNoDischarged = itemView.findViewById(R.id.no_radio_button);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(patientList.get(position));
                    }
                }
            });
        }

        public void setRadioButtonDischarged(boolean discharged) {
            if (discharged) {
                radioButtonDischarged.setChecked(true);
                radioButtonNoDischarged.setChecked(false);
            }
            else {
                radioButtonDischarged.setChecked(false);
                radioButtonNoDischarged.setChecked(true);
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Patient patient);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
