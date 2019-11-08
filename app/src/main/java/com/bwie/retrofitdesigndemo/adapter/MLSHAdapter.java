package com.bwie.retrofitdesigndemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bwie.retrofitdesigndemo.R;
import com.bwie.retrofitdesigndemo.entity.HomeMultipartEntity;

import java.util.List;

public class MLSHAdapter extends RecyclerView.Adapter<MLSHAdapter.VH> {

    private Context context;
    private List<HomeMultipartEntity.Home> list;

    public MLSHAdapter(Context context, List<HomeMultipartEntity.Home> list) {
        this.context = context;
        this.list = list;
    }



    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.mlsh_subitem_layout,parent,false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {

        Glide.with(context).load(list.get(position).masterPic).into(holder.iv);
       holder.priceTv.setText("¥:"+list.get(position).price);
       holder.nameTv.setText(list.get(position).commodityName);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class VH extends RecyclerView.ViewHolder{

        private ImageView iv;
        private TextView nameTv,priceTv;
        public VH(@NonNull View itemView) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.title_product);
            priceTv = itemView.findViewById(R.id.price_pruduct);
            iv = itemView.findViewById(R.id.iv_product);
        }
    }

}
