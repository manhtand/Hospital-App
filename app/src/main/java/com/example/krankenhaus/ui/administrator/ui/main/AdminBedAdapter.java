package com.example.krankenhaus.ui.administrator.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.krankenhaus.R;
import com.example.krankenhaus.srccode.entities.relations.BedAndPatient;

import java.util.ArrayList;
import java.util.List;

public class AdminBedAdapter extends RecyclerView.Adapter<AdminBedAdapter.BedAndPatientHolder> {
    private List<BedAndPatient> bedAndPatientList;
    private OnItemClickListener listener;

    AdminBedAdapter() {
        this.bedAndPatientList = new ArrayList<>();
    }

    @NonNull
    @Override
    public BedAndPatientHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bed_item, parent, false);
        return new BedAndPatientHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BedAndPatientHolder holder, int position) {
        if (bedAndPatientList.size() == 0) {
            return;
        }
        BedAndPatient bedAndPatient = bedAndPatientList.get(position);
        holder.textViewBedNumber.setText("Bed Number: " + Integer.toString(bedAndPatient.bed.getNumber()));

        if (bedAndPatient.patient != null)
        {
            holder.textViewPatientName.setText("Patient Name: " + bedAndPatient.patient.getName());
            if(bedAndPatient.patient.isDischarged()){
                holder.textViewPatientHealthStatus.setText("Health Status: Recoverd");
            }
            else{
                holder.textViewPatientHealthStatus.setText("Health Status: Not Recoverd");
            }
        }
        else {
            holder.textViewPatientName.setText("");
            holder.textViewPatientHealthStatus.setText("");
        }
    }

    @Override
    public int getItemCount() {
        return bedAndPatientList.size();
    }

    public void setBedList(List<BedAndPatient> bedAndPatientList) {
        this.bedAndPatientList = bedAndPatientList;
        notifyDataSetChanged();
    }

    class BedAndPatientHolder extends RecyclerView.ViewHolder {
        private TextView textViewBedNumber;
        private TextView textViewPatientName;
        private TextView textViewPatientHealthStatus;

        public BedAndPatientHolder(@NonNull View view) {
            super(view);
            textViewBedNumber = view.findViewById(R.id.bed_item_bed_number);
            textViewPatientName = view.findViewById(R.id.bed_item_patient_name);
            textViewPatientHealthStatus = view.findViewById(R.id.bed_item_health_status);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(bedAndPatientList.get(position));
                    }
                }
            });
        }
    }
    public interface OnItemClickListener {
        void onItemClick(BedAndPatient bedAndPatient);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
