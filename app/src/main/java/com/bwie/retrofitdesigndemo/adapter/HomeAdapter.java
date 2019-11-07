package com.bwie.retrofitdesigndemo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bwie.retrofitdesigndemo.R;
import com.bwie.retrofitdesigndemo.entity.HomeEntity;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MLSHViewholder> {
    private int MLSH = 0;
    private int RXXP = 1;
    private int PZSH = 2;
    private Context context;
    private List<HomeEntity.ResultBean.MlssBean.CommodityListBeanXX> list;
    public HomeAdapter(Context context, List<HomeEntity.ResultBean.MlssBean.CommodityListBeanXX> list) {
    this.context = context;
    this.list = list;
    }


    @Override
    public int getItemViewType(int position) {

        if (list.get(position).getCommodityId()==1){

            return MLSH;
        }else if (list.get(position).getCommodityId()==2){
            return 1;
        }else {
            return 2;
        }

    }


    @NonNull
    @Override
    public MLSHViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.home_item_layout,null);
        MLSHViewholder vh = new MLSHViewholder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MLSHViewholder holder, int position) {

        holder.nameTv.setText(list.get(position).getCommodityName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class  MLSHViewholder extends RecyclerView.ViewHolder{


        private TextView nameTv;
        public MLSHViewholder(@NonNull View itemView) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.name);
        }
    }
    class  RXXPViewholder extends RecyclerView.ViewHolder{

        public RXXPViewholder(@NonNull View itemView) {
            super(itemView);
        }
    }
    class  PZSHViewholder extends RecyclerView.ViewHolder{

        public PZSHViewholder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
