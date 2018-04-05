package com.example.mahe.manipallibrary;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

public class showlogs extends AppCompatActivity {
    logadapter adapter;
    librarydatabase db;
    RecyclerView loglist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showlogs);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db=new librarydatabase(this);
        loglist=(RecyclerView)findViewById(R.id.logs);
        loglist.setHasFixedSize(true);
        loglist.setLayoutManager(new LinearLayoutManager(this));
        final ArrayList<String> u=new ArrayList<>();
        final ArrayList<String> i=new ArrayList<>();
        final ArrayList<String> r=new ArrayList<>();
        final ArrayList<String> b=new ArrayList<>();
        adapter=new logadapter(u,i,r,b,this);
        loglist.setAdapter(adapter);
        Cursor c=db.getlogs();
        if(c!=null && c.moveToFirst())
        {
            do {
                u.add(c.getString(c.getColumnIndex("userid")));
                i.add(c.getString(c.getColumnIndex("issuedate")));
                r.add(c.getString(c.getColumnIndex("returndate")));
                b.add(c.getString(c.getColumnIndex("bookid")));
                adapter.notifyDataSetChanged();
            }while(c.moveToNext());
        }
    }

}
