package com.example.volley.services;

import com.example.volley.models.Learners;
import com.example.volley.models.SkillIq;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServices {

    @GET("api/skilliq")
    Call<List<SkillIq>>getSkillIqLeaders();

    @GET("api/hours")
    Call<List<Learners>>getTopLearners();
}
