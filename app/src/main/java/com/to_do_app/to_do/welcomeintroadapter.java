package com.to_do_app.to_do;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.to_do.R;

import java.util.ArrayList;

public class welcomeintroadapter extends RecyclerView.Adapter<welcomeintroadapter.viewholder> {
    Context context;
    ArrayList<welcomeintromodel> list;

    public welcomeintroadapter(Context context, ArrayList<welcomeintromodel> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public welcomeintroadapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewholder(LayoutInflater.from(context).inflate(R.layout.welcomeintrorecyclerlayout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull welcomeintroadapter.viewholder holder, int position) {
        holder.text1.setText(list.get(position).getHeading());
        holder.text2.setText(list.get(position).getDescription());
        holder.image.setImageResource(list.get(position).getImagelink());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        TextView text1,text2;
        ImageView image;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            text1=itemView.findViewById(R.id.textview1);
            text2= itemView.findViewById(R.id.textview2);
            image=itemView.findViewById(R.id.imageview);
        }
    }
}
