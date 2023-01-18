package com.example.krankenhaus.ui.service.labor.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.krankenhaus.R;
import com.example.krankenhaus.srccode.entities.BloodTest;
import com.example.krankenhaus.srccode.entities.relations.BedAndPatient;

import java.util.ArrayList;
import java.util.List;

public class BloodTestAdapter extends RecyclerView.Adapter<BloodTestAdapter.BloodTestHolder> {
    private List<BloodTest> allNewBloodTest;
    private OnItemClickListener listener;

    BloodTestAdapter(){
        this.allNewBloodTest = new ArrayList<>();
    }

    @NonNull
    @Override
    public BloodTestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bloodtest_item, parent, false);
        return new BloodTestHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BloodTestHolder holder, int position) {
        if(allNewBloodTest.size()==0){
            return;
        }
        BloodTest bloodTest = allNewBloodTest.get(position);
        //holder.textViewInsuranceNumber.setText();
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class BloodTestHolder extends RecyclerView.ViewHolder{
        private TextView textViewInsuranceNumber;
        private TextView textViewCreationDate;

        public BloodTestHolder(@NonNull View view){
            super(view);
            textViewInsuranceNumber = view.findViewById(R.id.blood_test_item_insurance_number);
            textViewCreationDate = view.findViewById(R.id.blood_test_item_creation_time);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    int position = getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION){
                        listener.onItemClick(allNewBloodTest.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(BloodTest bloodTest);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
