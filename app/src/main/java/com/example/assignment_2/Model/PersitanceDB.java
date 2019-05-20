package com.example.assignment_2.Model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class PersitanceDB extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "userdb";
    public static final int DATABASE_VERSION = 1;
    public PersitanceDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
        if (!isTableExisting(TableConstants.ClassicTable.TABLE_NAME))
            onCreate(db);
    }
    public void clearData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        if (isTableExisting(TableConstants.ClassicTable.TABLE_NAME))
            db.execSQL("DROP TABLE "+ TableConstants.ClassicTable.TABLE_NAME);
        onCreate(db);
    }


    //NOTE this is not called if there is an existing database file
    @Override
    public void onCreate(SQLiteDatabase db) {
        //CREATE TABLE TABLENAME

        db.execSQL(
                "CREATE TABLE " + TableConstants.ClassicTable.TABLE_NAME+
                        " ("+
                        TableConstants.ClassicTable._ID+
                        " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        TableConstants.ClassicTable.ARTIST_NAME_COLUMN +
                        " VARCHAR(255), "+
                        TableConstants.ClassicTable.TRACK_NAME_COLUMN +
                        " VARCHAR(255), "+
                        TableConstants.ClassicTable.SAMPLE_URI_COLUMN +
                        " VARCHAR(255), "+
                        TableConstants.ClassicTable.ARTWORK_URI_COLUMN +
                        " VARCHAR(255), "+
                        TableConstants.ClassicTable.TRACK_PRICE_COLUMN +
                        " VARCHAR(255))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(
                "DROP TABLE "+
                        TableConstants.ClassicTable.TABLE_NAME
        );
        onCreate(db);
    }

    //{from google}
    //From within a C/C++ program (or a script using Tcl/Ruby/Perl/Python bindings)
    //you can get access to table and index names by doing a SELECT on a special
    //table named "SQLITE_MASTER". Every SQLite database has an SQLITE_MASTER
    //table that defines the schema for the database. ... The SQLITE_MASTER table is read-only.
    public boolean isTableExisting(String tableName)
    {
        boolean isExisting = false;
        try{
            //this.getDatabaseName();
            Cursor cursor =
                    this.getReadableDatabase()
                            .rawQuery(
                                    "select DISTINCT tbl_name from sqlite_master where tbl_name = '"
                                            + TableConstants.ClassicTable.TABLE_NAME+"'",
                                    null);
            if(cursor!=null && cursor.getCount()>0) isExisting = true;
            cursor.close();
        }catch (SQLiteException e){
            if (e.getMessage().contains("no such table")){
                // Log.e(TAG, "Creating table " + TABLE_NAME + "because it doesn't exist!" );
                // create table
                // re-run query, etc.
            }
        }
        return isExisting;
    }




}
