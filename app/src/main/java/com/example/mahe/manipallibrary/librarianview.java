package com.example.mahe.manipallibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class librarianview extends AppCompatActivity {
    librarydatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_librarianview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       db=new librarydatabase(this);

    }
    public void status(View v)
    {
        Intent intent=new Intent(this,showstatus.class);
        startActivity(intent);
    }

public void list(View v)
{
    Intent intent=new Intent(this,listallbooks.class);
    startActivity(intent);

}
public  void bookadd(View v)
{
    Intent intent=new Intent(this,addbook.class);
    startActivity(intent);
}
public void logs(View v)
{
    Intent intent=new Intent(this,showlogs.class);
    startActivity(intent);
}
}
