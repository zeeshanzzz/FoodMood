package com.example.khan.foodmood.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

//import com.example.khan.foodgain2.Models.Items;
import com.bumptech.glide.Glide;
import com.example.khan.foodmood.FoodOrder;
import com.example.khan.foodmood.Model.Food;
import com.example.khan.foodmood.Model.Items;
import com.example.khan.foodmood.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_Adapter extends RecyclerView.Adapter<MainActivity_Adapter.MainHolder>{
  private   Context context;
  private List<Food> itemsList=new ArrayList<>();

    public MainActivity_Adapter(Context context, List<Food> itemsList) {
        this.context = context;
        this.itemsList = itemsList;
    }

    @NonNull
    @Override
    public MainHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
     View view=  LayoutInflater.from(context).inflate(R.layout.main1_layout ,parent, false);
        MainHolder mainHolder=new MainHolder(view);
        return mainHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull final MainHolder holder, final int position) {
        Glide.with(context).load(itemsList.get(position).getImage()).into(holder.imageView);
        holder.textView.setText(itemsList.get(position).getName());
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity_Adapter mainActivity_adapter;
                Intent intent=new Intent(context, FoodOrder.class);
                intent.putExtra("FoodId",holder.getAdapterPosition());
                context.startActivity(intent);


            }
        });




    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public class MainHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        ConstraintLayout constraintLayout;
        public MainHolder(View itemView) {
            super(itemView);
           imageView=itemView.findViewById(R.id.MainActivity_image);
           textView=itemView.findViewById(R.id.MainActivity_text);
           constraintLayout=itemView.findViewById(R.id.Food_constrain);
        }
    }

}
