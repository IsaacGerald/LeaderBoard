package com.example.volley;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.volley.adapters.MyPagerAdapter;
import com.example.volley.fragments.SkillIqFragment;
import com.example.volley.fragments.TopLearnersFragment;
import com.example.volley.models.SkillIq;
import com.example.volley.services.ApiServices;
import com.example.volley.services.ServiceBuilder;
import com.example.volley.viewmodel.LeadersViewModel;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.security.auth.Subject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public static final int LEARNERS_FRAGMENT = 0;
    public static final int SKILLS_FRAGMENT = 1;
    private LeadersViewModel mViewModel;
    private TextView mTextView;
    private SkillIqFragment mSkillIqFragment;
    private TopLearnersFragment mLearnersFragment;
    private ViewPager mViewPager;
    private MyPagerAdapter mPagerAdapter;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       mViewPager = findViewById(R.id.viewPager);

       setUpViewPager();



    }
    private void setUpViewPager(){
        mPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mLearnersFragment = new TopLearnersFragment();
        mSkillIqFragment = new SkillIqFragment();
        mPagerAdapter.addFragment(mLearnersFragment);
        mPagerAdapter.addFragment(mSkillIqFragment);

        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout = findViewById(R.id.tab_layout);
        mTabLayout.setupWithViewPager(mViewPager);

        mTabLayout.getTabAt(LEARNERS_FRAGMENT).setText("Learning Leaders");
        mTabLayout.getTabAt(SKILLS_FRAGMENT).setText("Skill Leaders");


    }


    public void submitButton(View view) {
        Intent intent = new Intent(MainActivity.this, ProjectSubmission.class);
        startActivity(intent);
    }
}