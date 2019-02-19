package com.example.khan.foodmood;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.khan.foodmood.Adapter.MainActivity_Adapter;
import com.example.khan.foodmood.ViewModel.MyViewModel;

import java.util.List;

public class Food extends AppCompatActivity {
    String Id;
    private MyViewModel  myViewModel;
    private RecyclerView recyclerView;
    private MainActivity_Adapter mainActivity_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        recyclerView=findViewById(R.id.Food_recycler);
        if(getIntent()!=null){
            Id=getIntent().getStringExtra("FoodId");
            myViewModel= ViewModelProviders.of(this).get(MyViewModel.class);
            myViewModel.Init(Id);
            myViewModel.Getdata().observe(this, new Observer<List<com.example.khan.foodmood.Model.Food>>() {
                @Override
                public void onChanged(@Nullable List<com.example.khan.foodmood.Model.Food> foods) {
                    mainActivity_adapter=new MainActivity_Adapter(Food.this,foods);
                    recyclerView.setAdapter(mainActivity_adapter);
                    mainActivity_adapter.notifyDataSetChanged();
                }
            });
            init();
        }

    }
    public void init(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);




    }
}
