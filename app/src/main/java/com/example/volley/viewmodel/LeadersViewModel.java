package com.example.volley.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.volley.models.Learners;
import com.example.volley.models.SkillIq;
import com.example.volley.services.ApiServices;
import com.example.volley.services.ServiceBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeadersViewModel extends AndroidViewModel {

    private MutableLiveData<List<SkillIq>> mSkillIqData = new MutableLiveData<>();
    private MutableLiveData<List<Learners>> mLearnersData = new MutableLiveData<>();

    public LeadersViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<SkillIq>> getAllSkillIq(){
        skillIqApiCall();
        return mSkillIqData;
    }
    public LiveData<List<Learners>> getLearners(){
       learnerApiCall();
        return mLearnersData;
    }
public void skillIqApiCall(){

    ApiServices mApiServices = ServiceBuilder.getInstance().buildService(ApiServices.class);
    Call<List<SkillIq>> mCall = mApiServices.getSkillIqLeaders();
    mCall.enqueue(new Callback<List<SkillIq>>() {
        @Override
        public void onResponse(Call<List<SkillIq>> call, Response<List<SkillIq>> response) {
            if (response != null){
                mSkillIqData.postValue(response.body());
            }


        }

        @Override
        public void onFailure(Call<List<SkillIq>> call, Throwable t) {
         mSkillIqData.postValue(null);
        }
    });

}
public void learnerApiCall(){
        ApiServices mApiServices = ServiceBuilder.getInstance().buildService(ApiServices.class);
        Call<List<Learners>> mCall = mApiServices.getTopLearners();
        mCall.enqueue(new Callback<List<Learners>>() {
            @Override
            public void onResponse(Call<List<Learners>> call, Response<List<Learners>> response) {
                mLearnersData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Learners>> call, Throwable t) {
                 mSkillIqData.postValue(null);
            }
        });
}

}
