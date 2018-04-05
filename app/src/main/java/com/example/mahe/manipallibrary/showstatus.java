package com.example.mahe.manipallibrary;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

public class showstatus extends AppCompatActivity {
    statusadapter adapter;
    librarydatabase db;
    RecyclerView stlist;
    String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showstatus);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        db=new librarydatabase(this);
        stlist=(RecyclerView)findViewById(R.id.recstatus);
        stlist.setHasFixedSize(true);
        stlist.setLayoutManager(new LinearLayoutManager(this));
        final ArrayList<String> name=new ArrayList<>();
        final ArrayList<String> dt=new ArrayList<>();
        final ArrayList<String> bname=new ArrayList<>();
        final ArrayList<String> uid=new ArrayList<>();
        adapter=new statusadapter(uid,dt,bname,this);
        stlist.setAdapter(adapter);
        Cursor c=db.listissue();
        //Log.d("isseue", String.valueOf(c.getCount()));
        if(c!=null && c.moveToFirst())
        {

            do{
            name.add(c.getString(c.getColumnIndex("name")));
            uid.add(c.getString(c.getColumnIndex("userid")));
            bname.add(c.getString(c.getColumnIndex("bookname")));
            dt.add(c.getString(c.getColumnIndex("issuedate")));
            adapter.notifyDataSetChanged();}while(c.moveToNext());
        }

    }

}
