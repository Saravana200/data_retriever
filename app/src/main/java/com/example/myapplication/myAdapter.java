package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class myAdapter extends RecyclerView.Adapter<myAdapter.myviewHolder> {

    ArrayList<data_retrieve> list=new ArrayList<>();
    Context context;
    public myAdapter(Context ct, ArrayList list)
    {
        context=ct;
        this.list=list;
        System.out.println(list.get(1));
    }

    @NonNull
    @Override
    public myviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater flater=LayoutInflater.from(context);
        View view=flater.inflate(R.layout.items,parent,false);
        return new myviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txt.setText(list.get(position).getDesc());
        Glide.with(context).load(list.get(position).getImage_url()).into(holder.img);
        holder.mainlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,MainActivity2.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("data1","this is just a description but in a much bigger way thanks for bearing with me");
                intent.putExtra("img1",list.get(position).getImage_url());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myviewHolder extends RecyclerView.ViewHolder{
        TextView txt;
        ImageView img;
        RelativeLayout mainlayout;
        public myviewHolder(@NonNull View itemView) {
            super(itemView);
            txt=itemView.findViewById(R.id.txt_view);
            img=itemView.findViewById(R.id.img_view);
            mainlayout=itemView.findViewById(R.id.MainLayout);
        }
    }
}
