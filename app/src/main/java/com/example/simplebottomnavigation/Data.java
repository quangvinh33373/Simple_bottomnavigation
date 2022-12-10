package com.example.simplebottomnavigation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Data extends SQLiteOpenHelper {
    SQLiteDatabase db=this.getReadableDatabase();
    private static final String DATABASE_NAME = "baithi";
    private static final int DATABASE_VERSION = 2;

    private static final String TABLE1 = "user";
    private static final String TABLE2 = "group_user";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_BIRTH = "ngaysinh";
    public Data(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "create table " + TABLE1 +
                "(" +
                KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                KEY_NAME + " text ," +
                KEY_BIRTH + " text " +

                ")";

        db.execSQL(sql);
        String sql2 = "create table " + TABLE2 +
                "(" +
                KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                KEY_NAME + " text " +

                ")";
        db.execSQL(sql2);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE1);
        onCreate(db);
    }


    public List<GroupObj> getGroup() {

        Cursor cursor = db.rawQuery("select * from " + TABLE2, null);

        List<GroupObj> list = new ArrayList<>();
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new GroupObj(cursor.getInt(0),cursor.getString(1)));
            } while (cursor.moveToNext());
        }
        return list;

    }
    public List<UserObj> getUser() {

        Cursor cursor = db.rawQuery("select * from " + TABLE1, null);

        List<UserObj> list = new ArrayList<>();
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new UserObj(cursor.getInt(0),cursor.getString(1),cursor.getString(2)));
            } while (cursor.moveToNext());
        }
        return list;

    }
    public long create_user(String username,String birth) {

        ContentValues values = new ContentValues();
        values.put(KEY_NAME,username );
        values.put(KEY_BIRTH,birth );
        return  db.insert(TABLE1, null, values);
    }
    public long edit_user(String username,String birth,int id) {

        ContentValues values = new ContentValues();
        values.put(KEY_NAME,username );
        values.put(KEY_BIRTH,birth );
        return db.update(TABLE1,values,"id=?", new String[]{String.valueOf(id)});
    }
    public long edit_group(String username,int id) {

        ContentValues values = new ContentValues();
        values.put(KEY_NAME,username );
        return db.update(TABLE2,values,"id=?", new String[]{String.valueOf(id)});
    }
    public void create_usergroup(String name){
        ContentValues values=new ContentValues();
        values.put(KEY_NAME,name);
         db.insert(TABLE2,null,values);
    }
    public void delete(int id,String loai) {
        if(loai=="user"){
            db.delete(TABLE1, "id=?", new String[]{String.valueOf(id)});
        }
       if(loai=="usergroup"){
           db.delete(TABLE2, "id=?", new String[]{String.valueOf(id)});
       }
        }

    }



