package com.feedbotretailapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by MyMac on 07/06/16.
 */
public class MyDatabase extends SQLiteOpenHelper{

    public static final String DBNAME = "MainData";
    public static final int VERSION = 1;
    Context context;


    public static final String table1="Query";
    public static final String table2="Result";
    public MyDatabase(Context c)
    {
        super(c,DBNAME,null,VERSION);
        context = c;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        try
        {
            String qryMain= "create table Query (Query1 TEXT,Query2 TEXT,Query3 TEXT,Query4 TEXT,Query5 TEXT,Query6 TEXT,Query7 TEXT,Query8 TEXT,Query9 TEXT,Query10 TEXT )";
            db.execSQL(qryMain);


            String qryProf= "create table Result (email TEXT, phone TEXT, suggestion TEXT, Query TEXT, quearyOption TEXT, QueryResult TEXT,QueryResultGraph TEXT)";
            db.execSQL(qryProf);

        }
        catch (Exception e)
        {
            Log.e("TAG : Table Creation",""+e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " +table1);
        db.execSQL("DROP TABLE IF EXISTS " +table2);
        onCreate(db);
    }
}
