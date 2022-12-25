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

import java.util.ArrayList;
import java.util.List;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.PatientHolder> {
    private List<Patient> patients = new ArrayList<>();

    @NonNull
    @Override
    public PatientHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.patient_item, parent, false);
        return new PatientHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientHolder holder, int position) {
        Patient currentPatient = patients.get(position);
        holder.textViewName.setText(currentPatient.getName());
        holder.setRadioButtonDischarged(currentPatient.isDischarged());

        holder.detailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PatientInfoFragment patientInfoFragment = new PatientInfoFragment();
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                activity.getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .addToBackStack(null)
                        .replace(R.id.nav_host_fragment_activity_doctor, patientInfoFragment)
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return patients.size();
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
        notifyDataSetChanged();
    }

    class PatientHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;
        private RadioButton radioButtonDischarged;
        private RadioButton radioButtonNoDischarged;
        private Button detailsButton;

        public PatientHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.patient_name);
            radioButtonDischarged = itemView.findViewById(R.id.yes_radio_button);
            radioButtonNoDischarged = itemView.findViewById(R.id.no_radio_button);
            detailsButton = itemView.findViewById(R.id.details_button);
        }

        public void setRadioButtonDischarged(boolean discharged) {
            if (discharged) {
                radioButtonDischarged.setChecked(true);
                radioButtonNoDischarged.setChecked(false);
            }
        }
    }
}
