package com.example.volley.adapters;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.volley.R;
import com.example.volley.models.SkillIq;

import java.util.ArrayList;
import java.util.List;

public class SkillIqAdapter extends RecyclerView.Adapter<SkillIqAdapter.SkillIqRecycleViewHolder> {
   private List<SkillIq> mIqList = new ArrayList<>();
    private Context mContext;



    @NonNull
    @Override
    public SkillIqRecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.skilliq_list_item, parent, false);
        SkillIqRecycleViewHolder holder = new SkillIqRecycleViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SkillIqRecycleViewHolder holder, int position) {
        SkillIq skillIq = mIqList.get(position);
        holder.mTvNameSkills.setText(skillIq.getName());
        String query = new StringBuilder()
                .append(skillIq.getScore()).append(" Skill IQ score, ")
                .append(skillIq.getCountry()).toString();
        holder.mTvScoreSkills.setText(query);
        Glide.with(holder.mImageView)
                .load(skillIq.getBadgeUrl())
                .into(holder.mImageView);

    }

    @Override
    public int getItemCount() {
        return mIqList.size();
    }
    public void updateSkillIq(List<SkillIq> skillIqs){
        if (skillIqs != null){
            mIqList.clear();
            mIqList.addAll(skillIqs);
            notifyDataSetChanged();
        }
    }

    public class SkillIqRecycleViewHolder extends RecyclerView.ViewHolder{
       private TextView mTvNameSkills, mTvScoreSkills;
       private ImageView mImageView;

        public SkillIqRecycleViewHolder(@NonNull View itemView) {
            super(itemView);

            mTvNameSkills = itemView.findViewById(R.id.tvNameSkill);
            mTvScoreSkills = itemView.findViewById(R.id.tvScoreSkill);
            mImageView = itemView.findViewById(R.id.skillIq_imageView);

        }
    }
}
