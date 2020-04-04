package com.example.covidapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidapp.R;
import com.example.covidapp.FaqsModel;



import java.util.ArrayList;
import java.util.List;

public class FaqsAdapter extends RecyclerView.Adapter<FaqsAdapter.FaqsViewHolder> {

    private List<FaqsModel> list;
    private List<Boolean> isOpenList;
    private int lastPosition = 0;

    public FaqsAdapter(List<FaqsModel> list) {
        this.list = list;
        isOpenList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            isOpenList.add(false);
        }
    }

    @NonNull
    @Override
    public FaqsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FaqsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items_guidelines, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FaqsViewHolder holder, int position) {
        FaqsModel faqsModel = list.get(position);
        holder.tvQues.setText(faqsModel.getQuestion());
        holder.tvAns.setText(faqsModel.getAnswer());
        changeState(holder.cv, position);
        if (isOpenList.get(position)) {
            holder.cvContent.setVisibility(View.VISIBLE);
        } else {
            holder.cvContent.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class FaqsViewHolder extends RecyclerView.ViewHolder {
        private CardView cv;
        private CardView cvContent;
        private TextView tvQues, tvAns;

        public FaqsViewHolder(@NonNull View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            cvContent = itemView.findViewById(R.id.cv_content);
            tvQues = itemView.findViewById(R.id.tv_ques);
            tvAns = itemView.findViewById(R.id.tv_ans);
        }
    }

    private void changeState(CardView cv, final int position){
        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOpenList.get(position)) {
                    isOpenList.set(position, false);
                    FaqsAdapter.this.notifyItemChanged(position);
                } else {
                    FaqsAdapter.this.resetStates();
                    isOpenList.set(position, true);
                    FaqsAdapter.this.notifyItemChanged(lastPosition);
                    FaqsAdapter.this.notifyItemChanged(position);
                }
            }
        });
    }

    private void resetStates() {
        for (int i = 0; i < isOpenList.size(); i++) {
            if (isOpenList.get(i)){
                lastPosition = i;
            }
            isOpenList.set(i, false);
        }
    }
}