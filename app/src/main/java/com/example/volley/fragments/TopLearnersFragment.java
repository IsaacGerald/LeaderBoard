package com.example.volley.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.volley.R;
import com.example.volley.adapters.LearnersAdapter;
import com.example.volley.models.Learners;
import com.example.volley.viewmodel.LeadersViewModel;

import java.util.ArrayList;
import java.util.List;


public class TopLearnersFragment extends Fragment {
    private static final String TAG = TopLearnersFragment.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private LearnersAdapter mLearnersAdapter;
    private List<Learners> mLearners = new ArrayList<>();
    private LeadersViewModel mViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View view = inflater.inflate(R.layout.fragment_top_learners, container, false);
       mViewModel = new ViewModelProvider(getActivity(),
               ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()))
               .get(LeadersViewModel.class);

       mRecyclerView = view.findViewById(R.id.learning_recyclerView);
       setUpRecyclerView();
       mViewModel.getLearners().observe(getActivity(), new Observer<List<Learners>>() {
           @Override
           public void onChanged(List<Learners> learners) {
               mLearnersAdapter.updateListIq(learners);
           }
       });


         return view;
    }

    private void setUpRecyclerView(){
        mLearnersAdapter = new LearnersAdapter();
        mRecyclerView.setAdapter(mLearnersAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,
                false));
    }
}