package com.example.krankenhaus.ui.service.labor.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.krankenhaus.R;
import com.example.krankenhaus.srccode.converter.LocalDataTimeConverter;
import com.example.krankenhaus.srccode.entities.relations.BloodTestAndRecord;

import java.util.ArrayList;
import java.util.List;

public class BloodTestAdapter extends RecyclerView.Adapter<BloodTestAdapter.BloodTestAndRecordHolder> {
    private List<BloodTestAndRecord> allNewBloodTestAndRecord;
    private OnItemClickListener listener;

    BloodTestAdapter(){
        this.allNewBloodTestAndRecord = new ArrayList<>();
    }

    @NonNull
    @Override
    public BloodTestAndRecordHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bloodtest_item, parent, false);
        return new BloodTestAndRecordHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BloodTestAndRecordHolder holder, int position) {
        if(allNewBloodTestAndRecord.size()==0){
            return;
        }
        BloodTestAndRecord bloodTestAndRecord = allNewBloodTestAndRecord.get(position);
        holder.textViewID.setText(Integer.toString(bloodTestAndRecord.bloodTest.getId()));
        holder.textViewInsuranceNumber.setText(bloodTestAndRecord.record.getPatientInsuranceNumber());
        holder.textViewCreationDate.setText(LocalDataTimeConverter.fromLocalDateTime(bloodTestAndRecord.bloodTest.getCreationTimestamp()));
    }

    @Override
    public int getItemCount() {
        return allNewBloodTestAndRecord.size();
    }

    public void setBloodTestAndRecordList(List<BloodTestAndRecord> bloodTestAndRecordList){
        this.allNewBloodTestAndRecord = bloodTestAndRecordList;
        notifyDataSetChanged();
    }
    class BloodTestAndRecordHolder extends RecyclerView.ViewHolder{
        private TextView textViewID;
        private TextView textViewInsuranceNumber;
        private TextView textViewCreationDate;

        public BloodTestAndRecordHolder(@NonNull View view){
            super(view);
            textViewID = view.findViewById(R.id.blood_test_item_blood_test_id);
            textViewInsuranceNumber = view.findViewById(R.id.blood_test_item_insurance_number);
            textViewCreationDate = view.findViewById(R.id.blood_test_item_creation_time);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    int position = getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION){
                        listener.onItemClick(allNewBloodTestAndRecord.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(BloodTestAndRecord bloodTestAndRecord);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
