package com.example.mahe.manipallibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class account extends AppCompatActivity {
    String name;
    String uid;

    librarydatabase db;
    RecyclerView booklist;
    booklistadapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        name=getIntent().getStringExtra("name");
        uid=getIntent().getStringExtra("uid");
        setTitle("Welcome,"+name);
        db=new librarydatabase(this);
       /*Cursor c=db.getbooks();
        Log.d("account","check");
        Log.d("account", String.valueOf(c.getCount()));
        if(c!=null && c.moveToFirst()){

        final ArrayList<String> bookname=new ArrayList<>();
       final ArrayList<String> authorname=new ArrayList<>();
            booklist=(RecyclerView)findViewById(R.id.listView);
            booklist.setHasFixedSize(true);
            booklist.setLayoutManager(new LinearLayoutManager(this));
            adapter=new booklistadapter(bookname,authorname,this);

            booklist.setAdapter(adapter);

        do {
            bookname.add(c.getString(c.getColumnIndex("bookname")));
            authorname.add(c.getString(c.getColumnIndex("author")));
            adapter.notifyDataSetChanged();


        }while(c.moveToNext());

        adapter.setOnItemClickListener(new booklistadapter.ClickListener() {
            @Override
            public void onClick(View item,int position) {
                int qty=db.getbookquantity(bookname.get(position),authorname.get(position));
                if(qty==0)
                {
                    Toast.makeText(getApplicationContext(),"book not available",Toast.LENGTH_SHORT).show();
                }else
                {
                    db.transact(uid,bookname.get(position),authorname.get(position));
                    Toast.makeText(getApplicationContext(),"values inserted",Toast.LENGTH_SHORT).show();
                }
            }
        });



        }
        else
        {
            Log.d("account","check here");
        }
*/


    }
    @Override
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        Intent setIntent = new Intent(this,MainActivity.class);

        startActivity(setIntent);
    }
    public void listbook(View v)
    {
        Intent intent=new Intent(this,listbooks.class);
        intent.putExtra("userid",uid);
        startActivity(intent);

    }
    public void adbook(View v)
    {
       Intent intent=new Intent(this,addbook.class);
        intent.putExtra("userid",uid);
        startActivity(intent);
    }
    public void seeissue(View v)
    {
        Intent intent=new Intent(this,issuebooksdisp.class);
        intent.putExtra("userid",uid);
        startActivity(intent);
    }

}
