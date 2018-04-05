package com.example.mahe.manipallibrary;

/**
 * Created by Mahe on 4/1/2018.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.example.mahe.manipallibrary.R.id.pass;

public class librarydatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName.db";

    public static int i=0;

    public librarydatabase(Context context) {
        super(context, DATABASE_NAME , null, 13);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table user(userid varchar(20) primary key,password varchar(20) not null check (length(password>5)),name varchar(20) not null,phone_number varchar(10) not null);"
        );
        db.execSQL("create table book(bookid varchar(20) ,bookname varchar(20),author varchar(20),qty number,description varchar(20),primary key(bookid,bookname,author));");
        db.execSQL("create table issue(bookid varchar(20),issuedate timestamp default current_timestamp,userid varchar(20),primary key(bookid,userid),foreign key(userid) references" +
                " user(userid),foreign key(bookid) references book(bookid));");
        db.execSQL("create table librarian(libid varchar(20) primary key,password varchar(20) not null,name varchar(20) not null,phone varchar(10) not null)");
        db.execSQL("create table logs(userid varchar(20),issuedate timestamp ,returndate timestamp,bookid varchar(20),primary key(userid,bookid,issuedate),foreign key(userid) references user(userid),foreign key(bookid) references book(bookid),foreign key(issuedate) references issue(issuedate))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("drop table if exists issue");
        db.execSQL("drop table if exists book");
        db.execSQL("drop table if exists librarian");
        db.execSQL("drop table if exists logs");
        onCreate(db);
    }

    public Cursor getlibaccount(String uid,String nm)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=null;
        try {
            res = db.rawQuery("select * from librarian where libid='" + uid + "' and " + "password='" + pass + "'", null);
            Log.d("lib", String.valueOf(res.getCount()));
            return res;
        }catch(Exception exc)
        {
            return null;
        }
    }
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
    public boolean insertUser (String userid, String pass, String name, String ph) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("insert into user values('" +
                userid + "','" +
                pass + "','" +
                name + "','" +
                ph + "');");
        /*ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("street", street);
        contentValues.put("place", place);
        db.insert("contacts", null, contentValues);
        SQLiteDatabase db1 = this.getReadableDatabase();
        try{
        Cursor res =  db1.rawQuery( "select * from user where userid='"+userid+"'", null );
        if (res!=null)
        {
            return false;
        }}
        catch(Exception exc) {
            try{

            db.execSQL("insert into user values('" +
                    userid + "','" +
                    pass + "','" +
                    name + "','" +
                    ph + "');");}
            catch( Exception e )
            {
                Log.d("exception",e.toString());
                return false;
            }
            return true;
        }*/
        return  true;
    }
    public void transact(String userid,String bookname,String authorname)
    {
        SQLiteDatabase db2=this.getWritableDatabase();
        SQLiteDatabase db=this.getReadableDatabase();
        db2.execSQL("update book set qty=qty-1 where bookname='"+bookname+"' and author='"+authorname+"'");
        Cursor c1=db.rawQuery("select bookid from book where bookname='"+bookname+"' and author='"+authorname+"'",null);
        c1.moveToFirst();
        String bookid=c1.getString(c1.getColumnIndex("bookid"));



            db2.execSQL("insert into issue values('" +
                    bookid+"'," +
                    "current_timestamp"+",'" +
                    userid+"')");
            Log.d("sexhfhgfhgyh",userid);




    }
    public Cursor getlogs()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c1=null;
        try{
            c1=db.rawQuery("select * from logs",null);
            return c1;
        }catch (Exception e)
        {
            return null;
        }
    }

    public void restorebook(String bookname,String author,String userid)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("update book set qty=qty+1 where bookname='"+bookname+"' and author='"+author+"'");
        SQLiteDatabase db1=this.getReadableDatabase();
        Cursor c=db1.rawQuery("select * from book where bookname='"+bookname+"' and author='"+author+"'",null);
        Log.d(";lil", String.valueOf(c.getCount()));
        String bookid=null;
        if (c!=null && c.moveToFirst())
        {
            bookid=c.getString(c.getColumnIndex("bookid"));
        }
        Cursor c2=db1.rawQuery("select * from issue where bookid='"+bookid+"' and userid='"+userid+"'",null);
        c2.moveToFirst();
        db.execSQL("insert into logs values('"+userid+"','"+c2.getString(c2.getColumnIndex("issuedate"))+"',current_timestamp,'"+bookid+"')");
        db.execSQL("delete from issue where bookid='"+bookid+"' and userid='"+userid+"'");

    }
    public int getbookquantity(String bookname,String author)
    {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res=null;

            res = db.rawQuery("select qty from book where bookname='" + bookname + "' and " + "author='" + author + "'", null);
            if (res!=null && res.moveToFirst())
                return res.getInt(res.getColumnIndex("qty"));

    return 0;

    }
    public Cursor getuseraccount(String uid,String pass)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res=null;
        try {
           res = db.rawQuery("select * from user where userid='" + uid + "' and " + "password='" + pass + "';", null);
            return res;
        }catch(Exception exc)
        {
            return null;
        }

    }
    public Cursor getissuebooks(String uid)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=null;
        try
        {
            res=db.rawQuery("select * from issue, book where issue.bookid=book.bookid and userid='"+uid+"'",null);
            res.moveToFirst();
            do {
                Log.d("anyuktha",res.getString(res.getColumnIndex("bookid")));
            }while(res.moveToNext());
            return res;

        }catch (Exception exc)
        {
            return null;
        }

    }
    public void insertintolib()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("insert into librarian values('lib3','lib3','lib2',12346)");
    }
    public Cursor getname(String uid)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=null;
        try {
            res = db.rawQuery("select * from user where userid='" + uid + "'", null);
            return res;
        }catch (Exception exc) {
        return null;
        }

    }
    public Cursor getbooks()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=null;
        try {
            res = db.rawQuery("select * from book", null);
            return  res;
        }
       catch (Exception exc)
       {
           Log.d("ec","lol");
           return null;
       }

    }
    public boolean insertbook(String bookname, String author, String publisher)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            Cursor c2=db.rawQuery("select * from book",null);
            int n=c2.getCount();
            Log.d("loliuyt", String.valueOf(n));
            Cursor res = db.rawQuery("select * from book where bookname='" + bookname + "' and author= '" + author + "'", null);
            if (res.getCount()==0)
            {
                db.execSQL("insert into book values('" +n+"','"+
                        bookname + "','" +
                        author + "',1,'" + publisher + "');");
            }else {
                db.execSQL("update book set qty=qty+1 where bookname='" + bookname + "' and author='" + author + "'");
            }
        }
        catch(Exception e) {
            db.execSQL("insert into book values('" +0+"','"+
                    bookname + "','" +
                    author + "',1,'" + publisher + "');");
        }

        return true;
    }

    public Cursor listissue() {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=null;

            c=db.rawQuery("select * from book natural join issue natural join user ",null);

            return c;


    }





    /*public ArrayList<String> getAllCotacts() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }*/
}