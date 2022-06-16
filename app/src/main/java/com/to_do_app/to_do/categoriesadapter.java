package com.to_do_app.to_do;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.to_do.R;

import java.util.ArrayList;

public class categoriesadapter extends RecyclerView.Adapter<categoriesadapter.viewholder> {
    Context context;
    ArrayList<categoriesmodel> list;
    ArrayList<groupoftasksmodel> rlist;
    int selectedindex=0;
    RecyclerView recyclerView;
    groupoftasksadapter adapter;
    public categoriesadapter(Context context, ArrayList<categoriesmodel> list,RecyclerView recyclerView) {
        this.context = context;
        this.list = list;
        this.recyclerView=recyclerView;
    }

    @NonNull
    @Override
    public categoriesadapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewholder(LayoutInflater.from(context).inflate(R.layout.categoriesrecyclerlayout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull categoriesadapter.viewholder holder, int position) {

        holder.text.setText(list.get(position).getText());
        list.get(position).pos=holder.getPosition();
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedindex=position;
                notifyDataSetChanged();
            }
        });
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);
                notifyDataSetChanged();
                adapter.notifyDataSetChanged();
            }
        });
        if(selectedindex==position){
            holder.layout.setBackgroundTintList(context.getResources().getColorStateList(R.color.darkmaincolor));
            holder.text.setTextColor(context.getResources().getColorStateList(R.color.white));
            list.get(position).selected=true;
            adapter=new groupoftasksadapter(list.get(position).getGrouptasks(),context);
            recyclerView.setAdapter(adapter);
        }else {
            holder.layout.setBackgroundTintList(context.getResources().getColorStateList(R.color.white));
            holder.text.setTextColor(context.getResources().getColorStateList(R.color.black));
            list.get(position).selected=false;
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        TextView text;
        LinearLayout layout;
        ImageView imageView;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            text=itemView.findViewById(R.id.text);
            layout=itemView.findViewById(R.id.layout);
            imageView=itemView.findViewById(R.id.imageview);
        }
    }
}
