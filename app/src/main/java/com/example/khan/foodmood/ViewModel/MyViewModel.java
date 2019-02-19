package com.example.khan.foodmood.ViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.khan.foodmood.Model.Food;
import com.example.khan.foodmood.Model.Items;
import com.example.khan.foodmood.Repo.MainActivity_Repo;

import java.util.List;

public class MyViewModel extends ViewModel {
    private MutableLiveData<List<Food>> Items;
    private MainActivity_Repo mainActivity_repo;

    public void Init(String id ){
        if(Items!=null){
            return;
        }
        mainActivity_repo=MainActivity_Repo.getInstance();

        Items=  mainActivity_repo.getItems(id);



    }
    public LiveData<List<Food>> Getdata(){
        return Items;
    }




}
