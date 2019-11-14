package com.bwie.retrofitdesigndemo.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

public class SQAdapter extends XRecyclerView.Adapter<RecyclerView.ViewHolder> {

   private int TYPE1 = 0;
   private int TYPE2 = 1;
   private int TYPE3 = 2;
   private int TYPE4 = 3;
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    int size = 0;
    @Override
    public int getItemViewType(int position) {
        if (size==0){
            return TYPE1;
        }else if (size==1){
            return TYPE2;
        }else if (size==2){
            return TYPE3;

        }else{
            return TYPE4;
        }
    }
}
