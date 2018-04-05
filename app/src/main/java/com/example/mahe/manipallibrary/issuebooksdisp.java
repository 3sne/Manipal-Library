package com.example.mahe.manipallibrary;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

public class issuebooksdisp extends AppCompatActivity {
    issueadapter adapter;
    librarydatabase db;
    RecyclerView issuelist;
    String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_issuebooksdisp);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        issuelist=(RecyclerView)findViewById(R.id.issuelist);
        issuelist.setHasFixedSize(true);
        issuelist.setLayoutManager(new LinearLayoutManager(this));
        final ArrayList<String> name=new ArrayList<>();
        final ArrayList<String> dt=new ArrayList<>();
        final ArrayList<String> author=new ArrayList<>();
        adapter=new issueadapter(name,dt,this);
        issuelist.setAdapter(adapter);
        userid=getIntent().getStringExtra("userid");
        db=new librarydatabase(this);
        Cursor c=db.getissuebooks(userid);
        if(c!=null && c.moveToFirst()){



        do {
            name.add(c.getString(c.getColumnIndex("bookname")));
            dt.add(c.getString(c.getColumnIndex("issuedate")));
            author.add(c.getString(c.getColumnIndex("author")));
            adapter.notifyDataSetChanged();
        }while(c.moveToNext());
            adapter.setOnItemClickListener(new issueadapter.ClickListener() {
                @Override
                public void onClick(View item, int position) {
                    db.restorebook(name.get(position),author.get(position),userid);
                    name.remove(position);
                    dt.remove(position);
                    author.remove(position);
                    adapter.notifyDataSetChanged();
                }
            });
        }
    }

}
