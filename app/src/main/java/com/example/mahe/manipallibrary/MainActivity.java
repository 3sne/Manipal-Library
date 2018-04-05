package com.example.mahe.manipallibrary;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    librarydatabase db;
    EditText uid;
    EditText pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=new librarydatabase(this);
        uid=(EditText)findViewById(R.id.username);
        pass=(EditText)findViewById(R.id.pass);
        //db.insertintolib();

    }
    public void go_to_sign_in(View view) {
        if (!uid.getText().toString().equals("") && !pass.getText().toString().equals("")) {
            Log.d("check", "go_to_sign_in: ");
            Cursor res = db.getuseraccount(String.valueOf(uid.getText()), String.valueOf(pass.getText()));

            if (res != null && res.moveToFirst()) {
                Log.d("lol", "here ");
                String name = res.getString(res.getColumnIndex("name"));
                Log.d("check name", name);
                String usid=uid.getText().toString();
                Intent intent = new Intent(this, account.class);
                intent.putExtra("name", name);
                intent.putExtra("uid", usid);
                uid.setText("");
                pass.setText("");
                Log.d("hello",name);
                res.close();
                startActivity(intent);


            } else {
                Toast.makeText(this, "you are not an existing user", Toast.LENGTH_SHORT).show();
                uid.setText("");
                pass.setText("");

            }
        }
        else
        {
            Toast.makeText(this,"please enter all fields",Toast.LENGTH_SHORT);

        }
    }

    public void go_to_create(View v)
    {
        Intent intent=new Intent(this,createaccount.class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void librlogin(View v)
    {
        if (!uid.getText().toString().equals("") && !pass.getText().toString().equals("")) {
            Log.d("check", "librlogin: ");
            Cursor res = db.getlibaccount(String.valueOf(uid.getText()), String.valueOf(pass.getText()));

            if (res != null && res.moveToFirst()) {
                Log.d("lol", "here ");
                String name = res.getString(res.getColumnIndex("name"));
                Log.d("check name", name);

                Intent intent = new Intent(this, librarianview.class);

                uid.setText("");
                pass.setText("");
                Log.d("hello",name);
                res.close();
                startActivity(intent);


            } else {
                Toast.makeText(this, "you are not a librarian", Toast.LENGTH_SHORT).show();
                uid.setText("");
                pass.setText("");
                Intent intent = new Intent(this, librarianview.class);
                startActivity(intent);



            }
        }
        else
        {
            Toast.makeText(this,"please enter all fields",Toast.LENGTH_SHORT);

        }
    }
    @Override
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        Intent setIntent = new Intent(Intent.ACTION_MAIN);
        setIntent.addCategory(Intent.CATEGORY_HOME);
        setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(setIntent);
    }
}
