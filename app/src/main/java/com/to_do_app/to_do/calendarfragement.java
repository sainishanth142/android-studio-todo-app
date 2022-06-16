package com.to_do_app.to_do;

import android.content.Context;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.to_do.R;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class calendarfragement extends Fragment {
    ArrayList<categoriesmodel> list1;
    ArrayList<groupoftasksmodel> list;
    ArrayList<groupoftasksmodel> rlist;
    ArrayList<taskmodel> rtlist;
    SharedPreferences mPrefs;
    RecyclerView recyclerView;
    groupoftasksadapter adapter;
    String date;
    CalendarView calendarView;
    @Override
    public void onStart() {
        super.onStart();
        setcontent(getView());
    }
    public void action(){
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rlist=new ArrayList<>();
        rtlist=new ArrayList<>();
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_calendarfragement, container, false);
        recyclerView=view.findViewById(R.id.recyclerview);
        calendarView=view.findViewById(R.id.calender);
        date=String.valueOf(new Date(calendarView.getDate()).getDate())+"-"+String.valueOf(new Date(calendarView.getDate()).getMonth()+1)+"-"+String.valueOf(Calendar.getInstance().get(java.util.Calendar.YEAR));
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                date=dayOfMonth+"-"+String.valueOf(month + 1)+"-"+year;
                setrecyclerviewcontent(list1);
                adapter.notifyDataSetChanged();
            }
        });
        return view;
    }
    private void setrecyclerviewcontent(@NonNull ArrayList<categoriesmodel> lis) {
        list=new ArrayList<>();
        for(int i = 0; i< lis.size(); i++){
            ArrayList<taskmodel> taskslist=new ArrayList<>();
            for(int j = 0; j< lis.get(i).getGrouptasks().size(); j++){
                if(Objects.equals(lis.get(i).getGrouptasks().get(j).getTitle(), date)){
                    for(int l=0;l<lis.get(i).getGrouptasks().get(j).getTasks().size();l++){
                        taskslist.add(lis.get(i).getGrouptasks().get(j).getTasks().get(l));
                        String ts=gettimestamp();
                        taskslist.get(l).id=ts;
                        lis.get(i).getGrouptasks().get(j).getTasks().get(l).id=ts;
                    }
                }
            }
            groupoftasksmodel l=new groupoftasksmodel(lis.get(i).getText(),taskslist);
            list.add(l);
        }
        setrecyclerview(list);
    }
    public String gettimestamp(){
        int time = (int) (System.currentTimeMillis());
        Timestamp tsTemp = new Timestamp(time);
        String ts =  tsTemp.toString();
        return ts;
    }
    private void setrecyclerview(ArrayList<groupoftasksmodel> list) {
        adapter=new groupoftasksadapter(list,getContext(),rlist);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
    private void setredata() {
        rtlist=adapter.rtlist;
        removetasks();
    }
    private void removetasks() {
        for(int l=0;l<rtlist.size();l++) {
            for (int i = 0; i < list1.size(); i++) {
                for (int j = 0; j < list1.get(i).getGrouptasks().size(); j++) {
                    for (int k = 0; k < list1.get(i).getGrouptasks().get(j).getTasks().size(); k++) {
                        if (list1.get(i).getGrouptasks().get(j).getTasks().get(k).getId() == rtlist.get(l).getId()) {
                            list1.get(i).getGrouptasks().get(j).getTasks().remove(k);
                            Toast.makeText(getContext(), String.valueOf(l), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

        }

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
        if(list1!=null)
        setrecyclerviewcontent(list1);
    }

    @Override
    public void onPause() {
        super.onPause();
        setredata();
    }
}