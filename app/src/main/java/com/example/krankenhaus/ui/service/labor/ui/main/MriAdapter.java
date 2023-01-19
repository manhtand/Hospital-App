package com.example.krankenhaus.ui.service.labor.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.krankenhaus.R;
import com.example.krankenhaus.srccode.converter.LocalDataTimeConverter;
import com.example.krankenhaus.srccode.entities.relations.MRIAndRecord;

import java.util.ArrayList;
import java.util.List;

public class MriAdapter extends RecyclerView.Adapter<MriAdapter.MRIAndRecordHolder> {
    private List<MRIAndRecord> allNewMRIAndRecord;
    private OnItemClickListener listener;

    MriAdapter(){
        this.allNewMRIAndRecord = new ArrayList<>();
    }

    @NonNull
    @Override
    public MRIAndRecordHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mri_item, parent, false);
        return new MRIAndRecordHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MRIAndRecordHolder holder, int position) {
        if(allNewMRIAndRecord.size()==0){
            return;
        }
        MRIAndRecord mriAndRecord = allNewMRIAndRecord.get(position);
        holder.textViewID.setText(Integer.toString(mriAndRecord.mri.getId()));
        holder.textViewInsuranceNumber.setText(mriAndRecord.record.getPatientInsuranceNumber());
        holder.textViewCreationDate.setText(LocalDataTimeConverter.fromLocalDateTime(mriAndRecord.mri.getCreationTimestamp()));
    }

    @Override
    public int getItemCount() {
        return allNewMRIAndRecord.size();
    }

    public void setMRIAndRecordList(List<MRIAndRecord> mriAndRecordList){
        this.allNewMRIAndRecord = mriAndRecordList;
        notifyDataSetChanged();
    }

    class MRIAndRecordHolder extends RecyclerView.ViewHolder{
        private TextView textViewID;
        private TextView textViewInsuranceNumber;
        private TextView textViewCreationDate;

        public MRIAndRecordHolder(@NonNull View view){
            super(view);
            textViewID = view.findViewById(R.id.mri_item_mri_id);
            textViewInsuranceNumber = view.findViewById(R.id.mri_item_insurance_number);
            textViewCreationDate = view.findViewById(R.id.mri_item_creation_time);


            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    int position = getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION){
                        listener.onItemClick(allNewMRIAndRecord.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(MRIAndRecord mriAndRecord);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
