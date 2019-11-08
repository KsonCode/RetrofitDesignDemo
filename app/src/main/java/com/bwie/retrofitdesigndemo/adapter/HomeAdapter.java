package com.bwie.retrofitdesigndemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bwie.retrofitdesigndemo.R;
import com.bwie.retrofitdesigndemo.entity.HomeEntity;
import com.bwie.retrofitdesigndemo.entity.HomeMultipartEntity;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //条目类型
    private int RXXP = 0;
    private int  MLSH= 1;
    private int  PZSH= 2;
    private Context context;
    private List<HomeMultipartEntity> list;
    public HomeAdapter(Context context, List<HomeMultipartEntity> list) {
    this.context = context;
    this.list = list;
    }

//
    @Override
    public int getItemViewType(int position) {

        if (list.get(position).type==0){

            return RXXP;
        }else if (list.get(position).type==1){

            return MLSH;
        }else{
            return PZSH;
        }




    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == RXXP) {
            View view1 = LayoutInflater.from(context).inflate(R.layout.rxxp_item_layout, parent, false);
            return new RXXPViewholder(view1);
        } else  if (viewType == MLSH){
            View view2 = LayoutInflater.from(context).inflate(R.layout.mlss_item_layout, parent, false);
            return new MLSHViewholder(view2);
        }else{
            View view3 = LayoutInflater.from(context).inflate(R.layout.pzsh_item_layout, parent, false);
            return new PZSHViewholder(view3);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof RXXPViewholder){

            ((RXXPViewholder) holder).rv.setLayoutManager(new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false));
            RXXPAdapter rxxpAdapter = new RXXPAdapter(context,list.get(position).list);
            ((RXXPViewholder) holder).rv.setAdapter(rxxpAdapter);



        }else if (holder instanceof MLSHViewholder){
            ((MLSHViewholder) holder).rv.setLayoutManager(new LinearLayoutManager(context));
            MLSHAdapter mlshAdapter = new MLSHAdapter(context,list.get(position).list);
            ((MLSHViewholder) holder).rv.setAdapter(mlshAdapter);
        }else {
            ((PZSHViewholder) holder).rv.setLayoutManager(new GridLayoutManager(context,2));
            PZSHAdapter mlshAdapter = new PZSHAdapter(context,list.get(position).list);
            ((PZSHViewholder) holder).rv.setAdapter(mlshAdapter);
        }

    }


    @Override
    public int getItemCount() {
        return 3;
    }

    class  MLSHViewholder extends RecyclerView.ViewHolder{


        private RecyclerView rv;
        public MLSHViewholder(@NonNull View itemView) {
            super(itemView);
            rv = itemView.findViewById(R.id.rv);
        }
    }
    class  RXXPViewholder extends RecyclerView.ViewHolder{

        private RecyclerView rv;


        public RXXPViewholder(@NonNull View itemView) {
            super(itemView);
            rv = itemView.findViewById(R.id.rv);
        }
    }
    class  PZSHViewholder extends RecyclerView.ViewHolder{
        private RecyclerView rv;
        public PZSHViewholder(@NonNull View itemView) {
            super(itemView);
            rv = itemView.findViewById(R.id.rv);
        }
    }
}
