package com.example.khan.foodmood.Repo;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.khan.foodmood.Model.Food;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_Repo {

        String name;
        String Url;
        private FirebaseDatabase firebaseDatabase;
        private DatabaseReference databaseReference;
        private List<Food> dataSet = new ArrayList<>();

        private static MainActivity_Repo Instance;
        public  static MainActivity_Repo  getInstance(){
            if(Instance==null){
                Instance=new MainActivity_Repo();
            }
            return Instance;

        }
        public MutableLiveData<List<Food>>  getItems(String Id){
            final MutableLiveData<List<Food>> data = new MutableLiveData<>();
            databaseReference=  FirebaseDatabase.getInstance().getReference().child("Foods");
            Query query=databaseReference.orderByChild("MenuId").equalTo(Id);
            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Food items = null;
                    for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                        if (snapshot.child("Name").getValue() != null) {
                            name = snapshot.child("Name").getValue().toString();
                        }
                        if (snapshot.child("Image").getValue() != null) {
                            Url = snapshot.child("Image").getValue().toString();
                        }
                        items=new Food(name,Url);
                        dataSet.add(items);

                    }

                    data.setValue(dataSet);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


            return data;


        }



    }


