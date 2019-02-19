package com.example.khan.foodmood.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khan.foodmood.ItemClickListner;
import com.example.khan.foodmood.R;

public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public ImageView imageView;
    public TextView textView;
    private ItemClickListner onItemClickListener;
    //private  Itemse
    public MyHolder(View itemView) {
        super(itemView);
        imageView=itemView.findViewById(R.id.MainActivity_image);
        textView=itemView.findViewById(R.id.MainActivity_text);
        itemView.setOnClickListener(this);
    }
    public void SetItemClick(ItemClickListner itemClickListner){
        this.onItemClickListener=itemClickListner;
    }

    @Override
    public void onClick(View v) {
        onItemClickListener.Onclick(v,getAdapterPosition(),false);

    }
}
