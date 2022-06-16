package com.to_do_app.to_do;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.to_do.R;

import java.util.ArrayList;

public class wellcome extends AppCompatActivity {
    RecyclerView recyclerView;
    welcomeintroadapter adapter;
    ArrayList<welcomeintromodel> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellcome);
        setRecyclerView();
    }
    public void setRecyclerView(){
        recyclerView=findViewById(R.id.recyclerview);
        list=new ArrayList<>();
        adapter=new welcomeintroadapter(this,list);
        welcomeintromodel item1=new welcomeintromodel(R.drawable.tasksicon,"Create tasks fast and easily","Input tasks, subtasks and repeating tasks.");
        list.add(item1);
        welcomeintromodel item2=new welcomeintromodel(R.drawable.reminder,"Task Reminders","Set reminders, and never miss important things.");
        list.add(item2);
        welcomeintromodel item3=new welcomeintromodel(R.drawable.widgetsicon,"Personal Widgets","Create widgets, and view your tasks more easily");
        list.add(item3);
        welcomeintromodel item4=new welcomeintromodel(R.drawable.tshirticon,"Custom Themes","Choose the themes you like and start your wonderful day.");
        list.add(item4);
        recyclerView.setAdapter(adapter);
    }

    public void gotohome(View view) {
        Intent intent=new Intent(wellcome.this,home.class);
        startActivity(intent);
        finish();
    }
}