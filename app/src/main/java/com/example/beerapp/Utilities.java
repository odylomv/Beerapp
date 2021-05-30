package com.example.beerapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

// This class is used to implement various methods about storing data via SQLite
public class Utilities extends SQLiteOpenHelper {
    public final static String FAV_TABLE = "FAV_TABLE";
    public final static String COMMENT_TABLE = "COMMENT_TABLE";
    public final static String FAV_ID = "ID";
    public final static String COMMENT_ID = "ID";
    public final static String COMMENT = "COMMENT";

    public Utilities(@Nullable Context context) {
        super(context, "beerApp.db", null, 1);
    }

    /* The database is made up of two tables, one where we store the ids (INTEGER) of the favorite
    beers and one where store the ids and the comments (TEXT) about beers */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE "+FAV_TABLE + "(" +FAV_ID +" INTEGER PRIMARY KEY)";
        String createTable2 = "CREATE TABLE "+COMMENT_TABLE+ "(" +COMMENT_ID +" INTEGER PRIMARY KEY,"+ COMMENT +" TEXT)";
        sqLiteDatabase.execSQL(createTable);
        sqLiteDatabase.execSQL(createTable2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) { }

    // Add beer id to favorites database table
    public boolean addFav(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(FAV_ID, id);
        long insert = db.insert(FAV_TABLE,null,cv);
        return insert != -1;
    }

    // Add beer comment to comments database table
    public boolean addComment(int id, String comment) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COMMENT_ID, id);
        cv.put(COMMENT, comment);
        long insert = db.insert(COMMENT_TABLE,null,cv);
        return insert != -1;
    }

    // Delete from favorites table
    public boolean deleteFav(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + FAV_TABLE + " WHERE " + FAV_ID + " = "+ id;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            cursor.close();
            db.close();
            return true; //found and deleted
        }
        else{
            cursor.close();
            db.close();
            return false; //not found and error
        }

    }

    // Get all the favorite beers
    public ArrayList<Integer> getFavorites() {
        ArrayList<Integer> favs = new ArrayList<>();
        String query = "SELECT * FROM " + FAV_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            do{
                int beerId = cursor.getInt(0);
                favs.add(beerId);
            }while (cursor.moveToNext());
        }
        // Else nothing to show in favorites

        cursor.close();
        db.close();
        return favs;
    }

    // Similar to getFavorites, only the ids of commented beers are returned
    public ArrayList<Integer> getCommented() {
        ArrayList<Integer> commented = new ArrayList<>();
        String query = "SELECT " +COMMENT_ID+ " FROM " + COMMENT_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            do{
                int beerId = cursor.getInt(0);
                commented.add(beerId);
            }while (cursor.moveToNext());
        }
        // Else nothing to show in comments

        cursor.close();
        db.close();
        return commented;
    }

    // Get specific comment depending on id
    public String getComment(int beerId) {
        String comment = "";
        String query = "SELECT " +COMMENT+ " FROM " + COMMENT_TABLE + " WHERE "+COMMENT_ID + "="+ beerId;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            comment = cursor.getString(cursor.getColumnIndex(COMMENT));
        }
        cursor.close();
        db.close();
        return comment;
    }

    // Utility function for deleting comments from the COMMENTS database table
    public boolean deleteComment(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + COMMENT_TABLE + " WHERE " + COMMENT_ID + " = "+ id;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            cursor.close();
            db.close();
            return true; //found and deleted
        }
        else{
            cursor.close();
            db.close();
            return false; //not found and error
        }
    }

    // Utility function for updating a comment record
    public void updateComment(int id, String comment) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COMMENT, comment);
        db.update(COMMENT_TABLE,cv,COMMENT_ID + " = "+ id,null);
    }
}