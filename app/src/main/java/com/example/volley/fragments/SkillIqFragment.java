package com.example.volley.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.volley.R;
import com.example.volley.adapters.SkillIqAdapter;
import com.example.volley.models.Learners;
import com.example.volley.models.SkillIq;
import com.example.volley.viewmodel.LeadersViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class SkillIqFragment extends Fragment {
   private RecyclerView mRecyclerView;
   private SkillIqAdapter mIqAdapter;
   private List<SkillIq> mIqList = new ArrayList<>();
   private LeadersViewModel mViewModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_skill_iq, container, false);
        mRecyclerView = view.findViewById(R.id.rvSkillIq);
        setUpRecycleView();

        mViewModel = new ViewModelProvider(getActivity(),
                ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()))
                .get(LeadersViewModel.class);

       mViewModel.getAllSkillIq().observe((LifecycleOwner) Objects.requireNonNull(getActivity()), new Observer<List<SkillIq>>() {
           @Override
           public void onChanged(List<SkillIq> skillIqs) {
               mIqAdapter.updateSkillIq(skillIqs);
           }
       });




        return view;
    }
    private void setUpRecycleView(){
        mIqAdapter = new SkillIqAdapter();
        mRecyclerView.setAdapter(mIqAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,
                false));
    }


}