package com.bwie.retrofitdesigndemo.adapter;

import android.content.Context;
import android.net.Uri;
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
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.VH> {

    private Context context;
    private List<LocalMedia> list;

    public PostAdapter(Context context,  List<LocalMedia> list) {
        this.context = context;
        this.list = list;
    }



    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.post_item_layout,parent,false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {

        Glide.with(context).load(Uri.fromFile(new File(list.get(position).getCompressPath()))).into(holder.iv);
//        Glide.with(context).load(list.get(position).getCompressPath()).into(holder.iv);



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class VH extends RecyclerView.ViewHolder{

        private ImageView iv;
        public VH(@NonNull View itemView) {
            super(itemView);

            iv = itemView.findViewById(R.id.iv);
        }
    }

}
