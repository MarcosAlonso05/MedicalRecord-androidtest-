// src/main/java/com/example/medicalrecord/MedicalRecordAdapter.java
package com.example.medicalrecord;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MedicalRecordAdapter extends RecyclerView.Adapter<MedicalRecordAdapter.RecordHolder> {
    private List<MedicalRecord> records = new ArrayList<>();

    @NonNull
    @Override
    public RecordHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);
        return new RecordHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordHolder holder, int position) {
        MedicalRecord currentRecord = records.get(position);
        holder.textView.setText(currentRecord.getName());
    }

    @Override
    public int getItemCount() {
        return records.size();
    }

    public void setRecords(List<MedicalRecord> records) {
        this.records = records;
        notifyDataSetChanged();
    }

    public MedicalRecord getRecordAt(int position) {
        return records.get(position);
    }

    class RecordHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public RecordHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        MedicalRecord clickedRecord = records.get(position);

                        Context context = v.getContext();
                        Intent intent = new Intent(context, RecordDetailActivity.class);

                        intent.putExtra(RecordDetailActivity.EXTRA_NAME, clickedRecord.getName());
                        intent.putExtra(RecordDetailActivity.EXTRA_AGE, clickedRecord.getAge());
                        intent.putExtra(RecordDetailActivity.EXTRA_CONDITION, clickedRecord.getCondition());

                        context.startActivity(intent);
                    }
                }
            });
        }
    }
}
