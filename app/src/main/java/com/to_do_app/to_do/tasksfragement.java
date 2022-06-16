package com.to_do_app.to_do;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.to_do.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Objects;

public class tasksfragement extends Fragment {
    categoriesadapter adapter1;
    ArrayList<categoriesmodel> list1;
    RecyclerView recyclerView1;
    ImageView imageView;
    FloatingActionButton addtaskbut;
    RecyclerView recyclerView2;
    groupoftasksadapter adapter2;
    ArrayList<taskmodel> tasklist;
    SharedPreferences mPrefs;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_tasksfragement, container, false);
        imageView=view.findViewById(R.id.menu);
        recyclerView2=view.findViewById(R.id.recyclerview2);
        addtaskbut=view.findViewById(R.id.addbutton);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addcategorie(view);
            }
        });
        addtaskbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addtask();
            }
        });
        setcontent(view);

        return view;
    }
    private void setcontent(View view) {
        list1=new ArrayList<>();
        mPrefs=getContext().getSharedPreferences("datashared", Context.MODE_PRIVATE);
        String[] json = mPrefs.getString("data2", "").split("-");
        for(int i=1;i<json.length;i++){
            String[] json1 = mPrefs.getString(json[i], "").split("@");
            ArrayList<groupoftasksmodel> l1=new ArrayList<>();
            for (int j=0;j<json1.length;j++){
                ArrayList<taskmodel> l2=new ArrayList<>();
                String[] data=json1[j].split("#");
                String t=data[0];
                for(int k=1;k<data.length;k++){
                    taskmodel tt;
                    String task=data[k].split("\\$")[0];
                    String time=data[k].split("\\$")[1];
                    if(Objects.equals(data[k].split("\\$")[2], "f")) tt=new taskmodel(task,time,false);
                    else tt=new taskmodel(task,time,true);
                    l2.add(tt);
                }
                if(!Objects.equals(t, "")){
                    groupoftasksmodel gt=new groupoftasksmodel(t,l2);
                    l1.add(gt);
                }
            }
            categoriesmodel item=new categoriesmodel(json[i],0,true,l1);
            list1.add(item);
        }
        recyclerView1=view.findViewById(R.id.recyclerview1);
        adapter1 = new categoriesadapter(getContext(),list1,recyclerView2);
        LinearLayoutManager lm=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView1.setLayoutManager(lm);
        recyclerView1.setAdapter(adapter1);
    }
    private void setrecyclerview2(View view) {
        recyclerView2=view.findViewById(R.id.recyclerview2);
    }
    private void addtask() {
        final String[] date = new String[1];
        final String[] time = new String[1];
        Dialog dialog=new Dialog(getContext());
        dialog.setContentView(R.layout.addtaskpopuplayout);
        dialog.show();
        dialog.findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int f=0;
                int pos=adapter1.selectedindex;
                EditText e=dialog.findViewById(R.id.input);
                if(!e.getText().toString().equals("")){
                    if(list1.size()>0){
                        for (int i=0;i<list1.get(pos).grouptasks.size();i++) {
                            if(Objects.equals(list1.get(pos).grouptasks.get(i).getTitle(), date[0])){
                                list1.get(pos).grouptasks.get(i).tasks.add(new taskmodel(e.getText().toString(),time[0],false));
                                f=1;
                                break;
                            }
                            f=0;
                        }
                    }
                    if(f==0){
                        ArrayList<taskmodel> lt=new ArrayList<>();
                        lt.add(new taskmodel(e.getText().toString(),time[0],false));
                        list1.get(pos).grouptasks.add(new groupoftasksmodel(date[0],lt));
                    }
                    adapter1.notifyDataSetChanged();
                    dialog.cancel();
                }else{
                    Toast.makeText(getContext(), "can't add empty task", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.findViewById(R.id.date).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog=new Dialog(getContext());
                dialog.setContentView(R.layout.selecttimepopuplayout);
                dialog.show();
                dialog.findViewById(R.id.cancle).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                dialog.findViewById(R.id.done).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        dialog.cancel();
                    }
                });
                CalendarView calender=dialog.findViewById(R.id.calender);
                calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(CalendarView view, int year, int month,
                                                    int dayOfMonth) {
                        String date1= dayOfMonth +"-"+(month+1)+"-"+year;
                        Toast.makeText(getContext(), date1, Toast.LENGTH_SHORT).show();
                        date[0]=date1;
                    }
                });
                dialog.findViewById(R.id.time).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Dialog dialog=new Dialog(getContext());
                        dialog.setContentView(R.layout.choosetimepopuplayout);
                        dialog.show();
                        dialog.findViewById(R.id.done).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                TimePicker tim=dialog.findViewById(R.id.timePicker);
                                time[0] =String.valueOf(tim.getCurrentHour())+":";
                                time[0] +=String.valueOf(tim.getCurrentMinute());
                                dialog.cancel();
                            }
                        });
                        dialog.findViewById(R.id.cancle).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.cancel();
                            }
                        });
                    }
                });
            }
        });
    }
    private void setrecyclerview1(View view) {

    }
    @Override
    public void onStart() {
        super.onStart();
        setcontent(getView());
    }
    public void addcategorie(View view) {
        Dialog dialog=new Dialog(getContext());
        dialog.setContentView(R.layout.categoriepopuplayout);
        dialog.show();
        EditText editTextd=dialog.findViewById(R.id.editText);
        dialog.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editTextd.getText().toString().equals("")){
                    ArrayList<groupoftasksmodel> l2=new ArrayList<>();
                    ArrayList<taskmodel> lt2=new ArrayList<>();
                    categoriesmodel item1=new categoriesmodel(editTextd.getText().toString(),0,false,l2);
                    list1.add(item1);
                    adapter1.notifyDataSetChanged();
                    dialog.cancel();
                }else{
                    Toast.makeText(getContext(), "can't add empty categorie", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public void onPause() {
        super.onPause();
        mPrefs=getContext().getSharedPreferences("datashared", Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        String res="";
        for(int i=0;i<list1.size();i++){
            res=res+"-"+list1.get(i).getText();
            String ap="";
            for(int j=0;j<list1.get(i).getGrouptasks().size();j++){
                String t=list1.get(i).getGrouptasks().get(j).getTitle();
                for(int k=0;k<list1.get(i).getGrouptasks().get(j).getTasks().size();k++){
                    if(list1.get(i).getGrouptasks().get(j).getTasks().get(k).getSlected())
                    t=t+"#"+list1.get(i).getGrouptasks().get(j).getTasks().get(k).getTask()+"$"+list1.get(i).getGrouptasks().get(j).getTasks().get(k).getDate()+"$"+"t";
                    else t=t+"#"+list1.get(i).getGrouptasks().get(j).getTasks().get(k).getTask()+"$"+list1.get(i).getGrouptasks().get(j).getTasks().get(k).getDate()+"$"+"f";
                }
                ap=ap+"@"+t;
            }
            prefsEditor.putString(list1.get(i).getText(), ap);
        }
        prefsEditor.putString("data2", res);
        prefsEditor.commit();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        String res="";
        for(int i=0;i<list1.size();i++){
            res=res+"-"+list1.get(i).getText();
            String ap="";
            for(int j=0;j<list1.get(i).getGrouptasks().size();j++){
                String t=list1.get(i).getGrouptasks().get(j).getTitle();
                for(int k=0;k<list1.get(i).getGrouptasks().get(j).getTasks().size();k++){
                    if(list1.get(i).getGrouptasks().get(j).getTasks().get(k).getSlected())
                        t=t+"#"+list1.get(i).getGrouptasks().get(j).getTasks().get(k).getTask()+"$"+list1.get(i).getGrouptasks().get(j).getTasks().get(k).getDate()+"$"+"t";
                    else t=t+"#"+list1.get(i).getGrouptasks().get(j).getTasks().get(k).getTask()+"$"+list1.get(i).getGrouptasks().get(j).getTasks().get(k).getDate()+"$"+"f";
                }
                ap=ap+"@"+t;
            }
            prefsEditor.putString(list1.get(i).getText(), ap);
        }
        prefsEditor.putString("data2", res);
        prefsEditor.commit();
    }

    @Override
    public void onStop() {
        super.onStop();
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        String res="";
        for(int i=0;i<list1.size();i++){
            res=res+"-"+list1.get(i).getText();
            String ap="";
            for(int j=0;j<list1.get(i).getGrouptasks().size();j++){
                String t=list1.get(i).getGrouptasks().get(j).getTitle();
                for(int k=0;k<list1.get(i).getGrouptasks().get(j).getTasks().size();k++){
                    if(list1.get(i).getGrouptasks().get(j).getTasks().get(k).getSlected())
                        t=t+"#"+list1.get(i).getGrouptasks().get(j).getTasks().get(k).getTask()+"$"+list1.get(i).getGrouptasks().get(j).getTasks().get(k).getDate()+"$"+"t";
                    else t=t+"#"+list1.get(i).getGrouptasks().get(j).getTasks().get(k).getTask()+"$"+list1.get(i).getGrouptasks().get(j).getTasks().get(k).getDate()+"$"+"f";
                }
                ap=ap+"@"+t;
            }
            prefsEditor.putString(list1.get(i).getText(), ap);
        }
        prefsEditor.putString("data2", res);
        prefsEditor.commit();
    }

}