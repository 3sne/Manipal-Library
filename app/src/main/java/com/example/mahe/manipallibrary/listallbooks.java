package com.example.mahe.manipallibrary;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

public class listallbooks extends AppCompatActivity {
    allbookadapter adapter;
    librarydatabase db;
    RecyclerView stlist;
    String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listallbooks);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        db=new librarydatabase(this);
        stlist=(RecyclerView)findViewById(R.id.listallbooks);
        stlist.setHasFixedSize(true);
        stlist.setLayoutManager(new LinearLayoutManager(this));
        final ArrayList<String> bname=new ArrayList<>();
        final ArrayList<String> aut=new ArrayList<>();
        final ArrayList<String> qty=new ArrayList<>();
        adapter=new allbookadapter(bname,aut,qty,this);
        stlist.setAdapter(adapter);
        Cursor c=db.getbooks();
        if (c!=null && c.moveToFirst())
        {
            do{
                bname.add(c.getString(c.getColumnIndex("bookname")));
                aut.add(c.getString(c.getColumnIndex("author")));
               qty.add(String.valueOf(c.getInt(c.getColumnIndex("qty"))));
                adapter.notifyDataSetChanged();}while(c.moveToNext());
        }
    }

}
