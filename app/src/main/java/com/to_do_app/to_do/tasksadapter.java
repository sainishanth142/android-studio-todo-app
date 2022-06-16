package com.to_do_app.to_do;
import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.to_do.R;

import java.util.ArrayList;
public class tasksadapter extends RecyclerView.Adapter<tasksadapter.viewholder> {
    Context context;
    ArrayList<taskmodel> list;

    TextView text1,text2;
    ImageView imageView;
    CheckBox checkBox;
    int p;
    public tasksadapter(Context context, ArrayList<taskmodel> list) {
        this.context = context;
        this.list = list;

    }
    @NonNull
    @Override
    public tasksadapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewholder(LayoutInflater.from(context).inflate(R.layout.taskviewlayout,parent,false));
    }
    @Override
    public void onBindViewHolder(@NonNull tasksadapter.viewholder holder, int position) {
        text1.setText(list.get(position).getTask());
        text2.setText(list.get(position).getDate());
        if(list.get(position).getSlected()){
            text1.setPaintFlags(text1.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            checkBox.setChecked(true);
        }else{
            text1.setPaintFlags(text1.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
            checkBox.setChecked(false);
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p=position;
                list.remove(position);
                notifyDataSetChanged();
            }
        });
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                list.get(position).slected=isChecked;
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
            text1=itemView.findViewById(R.id.text1);
            text2=itemView.findViewById(R.id.text2);
            checkBox=itemView.findViewById(R.id.checkbox);
            imageView=itemView.findViewById(R.id.imageview);
        }
    }
}
