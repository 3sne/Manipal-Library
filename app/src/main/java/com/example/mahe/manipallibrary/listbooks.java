package com.example.mahe.manipallibrary;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class listbooks extends AppCompatActivity {

    String uid;

    librarydatabase db;
    RecyclerView booklist;
    booklistadapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listbooks);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        uid=getIntent().getStringExtra("userid");
        setSupportActionBar(toolbar);
        db=new librarydatabase(this);
        Cursor c=db.getbooks();
        Log.d("account","check");
        Log.d("account", String.valueOf(c.getCount()));
        if(c!=null && c.moveToFirst()){

            final ArrayList<String> bookname=new ArrayList<>();
            final ArrayList<String> authorname=new ArrayList<>();
            final ArrayList<Integer> qt=new ArrayList<>();
            booklist=(RecyclerView)findViewById(R.id.ListView);
            booklist.setHasFixedSize(true);
            booklist.setLayoutManager(new LinearLayoutManager(this));
            adapter=new booklistadapter(bookname,authorname,qt,this);

            booklist.setAdapter(adapter);

            do {
                int qty=c.getInt(c.getColumnIndex("qty"));

                    qt.add(qty);
                    bookname.add(c.getString(c.getColumnIndex("bookname")));
                    authorname.add(c.getString(c.getColumnIndex("author")));


                    adapter.notifyDataSetChanged();





            }while(c.moveToNext());

            adapter.setOnItemClickListener(new booklistadapter.ClickListener() {
                @Override
                public void onClick(View item, int position) {
                    int qty=db.getbookquantity(bookname.get(position),authorname.get(position));
                    if(qty==0)
                    {
                        Toast.makeText(getApplicationContext(),"Book Unavailable :(",Toast.LENGTH_SHORT).show();
                    }else
                    {
                        db.transact(uid,bookname.get(position),authorname.get(position));
                        Toast.makeText(getApplicationContext(),"Book Issued!",Toast.LENGTH_SHORT).show();
                    }
                }
            });



        }
        else
        {
            Log.d("account","check here");
        }
    }

}
