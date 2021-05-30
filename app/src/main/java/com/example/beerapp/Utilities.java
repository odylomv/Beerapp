package com.example.beerapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

/**
 * This class is used to implement various methods about storing data via SQLite
 */
public class Utilities extends SQLiteOpenHelper {
    public final static String FAV_TABLE = "FAV_TABLE"; //table name, and columns
    public final static String COMMENT_TABLE = "COMMENT_TABLE";
    public final static String FAV_ID = "ID";
    public final static String COMMENT_ID = "ID";
    public final static String COMMENT = "COMMENT";

    public Utilities(@Nullable Context context) {
        super(context, "beerApp.db", null, 1);
    }

    /**
     * The database is made up of two tables, one where we store the ids (INTEGER) of the favorite beers
     * and one where store the ids and the comments (TEXT) about beers
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE "+FAV_TABLE + "(" +FAV_ID +" INTEGER PRIMARY KEY)";
        String createTable2 = "CREATE TABLE "+COMMENT_TABLE+ "(" +COMMENT_ID +" INTEGER PRIMARY KEY,"+ COMMENT +" TEXT)";
        sqLiteDatabase.execSQL(createTable);
        sqLiteDatabase.execSQL(createTable2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) { }
    

    /**
     * Add beer id to favorites table
     * @param id to be added
     * @return to check if insertion worked
     */
    public void addFav(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(FAV_ID, id);
        db.insert(FAV_TABLE,null, cv);
    }

    /**
     * similar to addFav
     * @param id to be added
     * @param comment
     * @return
     */
    public boolean addComment(int id, String comment) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COMMENT_ID, id);
        cv.put(COMMENT, comment);
        long insert = db.insert(COMMENT_TABLE,null,cv);
        return insert != -1;
    }

    /**
     * Delete from favorites table
     * @param id to be deleted
     * @return bool to see if it worked
     */
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

    /**
     * Get all the favorite beers
     * @return arraylist of integer ids
     */
    public ArrayList<Integer> getFavorites() {
        ArrayList<Integer> favs = new ArrayList<>();
        String query = "SELECT * FROM " + FAV_TABLE;
        SQLiteDatabase db = this.getReadableDatabase(); // readable because we only read data
        Cursor cursor = db.rawQuery(query, null); //cursors store the data from the select query
        if (cursor.moveToFirst()){
            do{
                int beerId = cursor.getInt(0);
                favs.add(beerId);
            }while (cursor.moveToNext());
        }
        else {
            //nothing to show in favorites
        }
        cursor.close();
        db.close();
        return favs;
    }

    /**
     * Similar to getFavorites, only the ids of commented beers are returned
     * @return Arraylist of integers
     */
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
        else {
            //nothing to show in comments
        }
        cursor.close();
        db.close();
        return commented;
    }

    /**
     * Get specific comment depending on Id
     * @param beerId id of desired beer
     * @return specific comment
     */
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

    /**
     * Utility function for deleting comments from the COMMENTS table
     * @param id of the deleted to be beer
     * @return bool whether it worked or not
     */
    public void deleteComment(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + COMMENT_TABLE + " WHERE " + COMMENT_ID + " = "+ id;
        Cursor cursor = db.rawQuery(query, null);
        cursor.close();
        db.close();

    }

    /**
     * Utility function for updating a comment record
     * @param id of the beer whose comment is to be updated
     * @param comment the updated text
     */
    public void updateComment(int id, String comment) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COMMENT, comment);
        db.update(COMMENT_TABLE,cv,COMMENT_ID + " = "+ id,null);
    }

}
