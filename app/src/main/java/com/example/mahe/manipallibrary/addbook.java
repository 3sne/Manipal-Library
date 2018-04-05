package com.example.mahe.manipallibrary;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class addbook extends AppCompatActivity {
    EditText bname;
    EditText aut;
    EditText des;

    librarydatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addbook);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

   bname=(EditText)findViewById(R.id.createbook);
        aut=(EditText)findViewById(R.id.createauthor);
        des=(EditText)findViewById(R.id.createdis);
        db=new librarydatabase(this);

    }
    public void add(View v)
    {
        db.insertbook(bname.getText().toString(),aut.getText().toString(),des.getText().toString());

        String userid=getIntent().getStringExtra("userid");
        if(userid==null)
        {
            Intent intent=new Intent(this,librarianview.class);
            startActivity(intent);
        }else
        {
            Intent intent=new Intent(this,account.class);
            intent.putExtra("uid",userid);
            Cursor res=db.getname(userid);
            Log.d("uytre","poiuyt");
            if(res!=null && res.moveToFirst())
                intent.putExtra("name",res.getString(res.getColumnIndex("name")));
            startActivity(intent);
        }
        }

    }
