package com.example.volley.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.volley.R;
import com.example.volley.models.Learners;

import java.util.ArrayList;
import java.util.List;

public class LearnersAdapter extends RecyclerView.Adapter<LearnersAdapter.LearnersRecyclerViewHolder> {
    private List<Learners> mLearners = new ArrayList<>();
    private Context mContext;

    @NonNull
    @Override
    public LearnersRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.learners_list_item,
                parent, false);
        LearnersRecyclerViewHolder holder = new LearnersRecyclerViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull LearnersRecyclerViewHolder holder, int position) {
        Learners learners = mLearners.get(position);
        holder.mTvName.setText(learners.getName());
        String query = new StringBuilder().append(learners.getHours())
                .append(" Learning hours, ")
                .append(learners.getCountry()).toString();
        holder.mTvHours.setText(query);

        Glide.with(holder.mImageView)
                .load(learners.getBadgeUrl())
                .into(holder.mImageView);


    }

    @Override
    public int getItemCount() {
        return mLearners.size();
    }
    public void updateListIq (List<Learners> learners){
        if (learners != null){
            mLearners.clear();
            mLearners.addAll(learners);
            notifyDataSetChanged();
        }
    }


    public class LearnersRecyclerViewHolder extends RecyclerView.ViewHolder{

        private TextView mTvName, mTvHours;
        private ImageView mImageView;

        public LearnersRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvName = itemView.findViewById(R.id.tvNameLearner);
            mTvHours = itemView.findViewById(R.id.tvHoursLearner);
            mImageView = itemView.findViewById(R.id.learning_imageView);
        }
    }
}
