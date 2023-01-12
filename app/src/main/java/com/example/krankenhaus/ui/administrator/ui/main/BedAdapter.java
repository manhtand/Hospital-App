package com.example.krankenhaus.ui.administrator.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;
import androidx.room.Query;
import androidx.room.Relation;

import com.example.krankenhaus.srccode.dao.BedDao;
import com.example.krankenhaus.srccode.entities.Bed;
import com.example.krankenhaus.R;
import com.example.krankenhaus.srccode.entities.relations.PatientAndBed;
import com.example.krankenhaus.ui.doctor.ui.main.DoctorViewModel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class BedAdapter extends RecyclerView.Adapter<BedAdapter.BedHolder> {
    private List<Bed> bedList;
    BedDao bedDao;

    BedAdapter() {
        this.bedList = new ArrayList<>();
    }

    @NonNull
    @Override
    public BedHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bed_item, parent, false);
        return new BedHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BedHolder holder, int position) {
        if (bedList.size() == 0) {
            return;
        }
        Bed bed = bedList.get(position);
        holder.textViewBedNumber.setText(Integer.toString(bed.getNumber()));

        if (bedDao.getPatientNameFromBed() != null && bedDao.getPatientInsuranceNumberFromBed() != null)
        {
            holder.textViewPatientName.setText(bedDao.getPatientNameFromBed().getValue());
            holder.textViewPatientInsuranceNumber.setText(bedDao.getPatientInsuranceNumberFromBed().getValue());
        }
        else {
            holder.textViewPatientName.setText("");
            holder.textViewPatientInsuranceNumber.setText("");
        }
    }

    @Override
    public int getItemCount() {
        return bedList.size();
    }

    public void setBedList(List<Bed> bedList) {
        this.bedList = bedList;
        notifyDataSetChanged();
    }

    class BedHolder extends RecyclerView.ViewHolder {
        private TextView textViewBedNumber;
        private TextView textViewPatientName;
        private TextView textViewPatientInsuranceNumber;

        public BedHolder(@NonNull View view) {
            super(view);
            textViewBedNumber = view.findViewById(R.id.bedlist_bed_number);
            textViewPatientName = view.findViewById(R.id.bedlist_patient_name);
            textViewPatientInsuranceNumber = view.findViewById(R.id.bedlist_patient_insurance_number);
        }
    }
}
