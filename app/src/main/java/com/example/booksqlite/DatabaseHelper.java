package com.example.booksqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "book_db";
    private static final  String TABLE_TEN = "book";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableTen = "CREATE TABLE "
                                + TABLE_TEN
                                +" (" + "id integer primary key autoincrement,"
                                        + "name text,"
                                        + "author text,"
                                        + "discription text)";
        db.execSQL(createTableTen);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists "+TABLE_TEN);
        onCreate(db);
    }

    public void addBook(Book book){
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("name",book.getName());
        values.put("author",book.getAuthor());
        values.put("discription",book.getDiscription());
        database.insert(TABLE_TEN,null,values);
    }

    public List<Book> getAllBook(){
        List<Book> list = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM "+TABLE_TEN,null);
        while(cursor.moveToNext()){
            list.add(new Book(cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3))
                    );
        }
        return  list;
    }

    public void deleteBook(int id){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id",id);
        database.delete(TABLE_TEN,"id = "+id,null);
    }
}