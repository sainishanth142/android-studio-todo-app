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

public class groupoftasksadapter extends RecyclerView.Adapter<groupoftasksadapter.viewholder> {
    ArrayList<groupoftasksmodel> list;
    Context context;
    tasksadapter adapter;
    ArrayList<taskmodel> taskslist;
    RecyclerView recyclerView;
    TextView textView;
    ImageView imageView;
    public groupoftasksadapter(ArrayList<groupoftasksmodel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public groupoftasksadapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewholder(LayoutInflater.from(context).inflate(R.layout.groupoftaskslayout,parent,false));
    }
    @Override
    public void onBindViewHolder(@NonNull groupoftasksadapter.viewholder holder, int position) {
        taskslist=list.get(position).getTasks();
        adapter=new tasksadapter(context,taskslist);
        recyclerView.setAdapter(adapter);
        textView.setText(list.get(position).getTitle());
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        public viewholder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageview);
            recyclerView=itemView.findViewById(R.id.recyclerview);
            textView=itemView.findViewById(R.id.text);
        }
    }
}
