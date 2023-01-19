package com.example.krankenhaus.ui.service.labor.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.krankenhaus.R;
import com.example.krankenhaus.srccode.entities.relations.BloodTestAndRecord;
import com.example.krankenhaus.srccode.entities.relations.MRIAndRecord;
import com.example.krankenhaus.srccode.entities.relations.RecordAndPatient;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class LaborExaminationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Object> examinationList;
    private List<MRIAndRecord> mriAndRecordList;
    private List<BloodTestAndRecord> bloodTestAndRecordList;

    LaborViewModel laborViewModel;
    RecordAndPatient recordAndPatient;
    LifecycleOwner lifecycleOwner;

    private OnItemClickListener listener;

    public LaborExaminationAdapter(LaborViewModel laborViewModel, LifecycleOwner lifecycleOwner) {
        this.examinationList = new ArrayList<>();
        this.laborViewModel = laborViewModel;
        this.lifecycleOwner = lifecycleOwner;

        laborViewModel.getAllNewMRIAndRecord().observe(lifecycleOwner, new Observer<List<MRIAndRecord>>() {
            @Override
            public void onChanged(List<MRIAndRecord> mriAndRecords) {
                examinationList.addAll(mriAndRecords);
                notifyDataSetChanged();
            }
        });

        laborViewModel.getAllNewBloodTestAndRecord().observe(lifecycleOwner, new Observer<List<BloodTestAndRecord>>() {
            @Override
            public void onChanged(List<BloodTestAndRecord> bloodTestAndRecords) {
                examinationList.addAll(bloodTestAndRecords);
                notifyDataSetChanged();
            }
        });
    }

    class MriAndRecordHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;
        private TextView textViewDate;
        private TextView textViewPatientName;
        private TextView textViewBedNumber;

        public MriAndRecordHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.doctor_exam_name);
            textViewDate = itemView.findViewById(R.id.doctor_exam_date);
            textViewPatientName = itemView.findViewById(R.id.doctor_exam_patient_name);
            textViewBedNumber = itemView.findViewById(R.id.doctor_exam_bed_number);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(examinationList.get(position));
                    }
                }
            });
        }
    }

    class BloodTestAndRecordHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;
        private TextView textViewDate;
        private TextView textViewPatientName;
        private TextView textViewBedNumber;

        public BloodTestAndRecordHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.doctor_exam_name);
            textViewDate = itemView.findViewById(R.id.doctor_exam_date);
            textViewPatientName = itemView.findViewById(R.id.doctor_exam_patient_name);
            textViewBedNumber = itemView.findViewById(R.id.doctor_exam_bed_number);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(examinationList.get(position));
                    }
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (examinationList.get(position) instanceof MRIAndRecord) {
            return 0;
        }
        else if (examinationList.get(position) instanceof BloodTestAndRecord) {
            return 1;
        }
        return -1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.doctor_examination_item, parent, false);

        switch (viewType) {
            case 0: return new MriAndRecordHolder(itemView);
            case 1: return new BloodTestAndRecordHolder(itemView);
            default: break;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        switch (getItemViewType(position)) {
            case 0:
                MriAndRecordHolder mriAndRecordHolder = (MriAndRecordHolder) holder;
                MRIAndRecord mriAndRecord = (MRIAndRecord) examinationList.get(position);

                laborViewModel.getRecordAndPatientByRecordID(mriAndRecord.record.getRecordId()).observe(lifecycleOwner, new Observer<RecordAndPatient>() {
                    @Override
                    public void onChanged(RecordAndPatient input) {
                        recordAndPatient = input;
                        mriAndRecordHolder.textViewName.setText("MRI");
                        mriAndRecordHolder.textViewDate.setText(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(mriAndRecord.mri.getCreationTimestamp()));
                        mriAndRecordHolder.textViewPatientName.setText(recordAndPatient.patient.getName());
                        mriAndRecordHolder.textViewBedNumber.setText(Integer.toString(recordAndPatient.patient.getBedNumber()));
                    }
                });
                break;

            case 1:
                BloodTestAndRecordHolder bloodTestAndRecordHolder = (BloodTestAndRecordHolder) holder;
                BloodTestAndRecord bloodTestAndRecord = (BloodTestAndRecord) examinationList.get(position);

                laborViewModel.getRecordAndPatientByRecordID(bloodTestAndRecord.record.getRecordId()).observe(lifecycleOwner, new Observer<RecordAndPatient>() {
                    @Override
                    public void onChanged(RecordAndPatient input) {
                        recordAndPatient = input;
                        bloodTestAndRecordHolder.textViewName.setText("Blood Test");
                        bloodTestAndRecordHolder.textViewDate.setText(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(bloodTestAndRecord.bloodTest.getCreationTimestamp()));
                        bloodTestAndRecordHolder.textViewPatientName.setText(recordAndPatient.patient.getName());
                        bloodTestAndRecordHolder.textViewBedNumber.setText(Integer.toString(recordAndPatient.patient.getBedNumber()));
                    }
                });
                break;
        }
    }

    @Override
    public int getItemCount() { return examinationList.size(); }

    public void setExaminationList(List<Object> examinationList) {
        this.examinationList = examinationList;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(Object object);
    }

    public void setOnItemClickListener(OnItemClickListener listener) { this.listener = listener; }
}
