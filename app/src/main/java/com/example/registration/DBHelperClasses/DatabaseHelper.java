package com.example.registration.DBHelperClasses;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.registration.ModelClass.UserDataResponse;

import java.util.ArrayList;


public class DatabaseHelper extends SQLiteOpenHelper {

    public final static String DATABASE_NAME = "DemoDB.db";
    public final static String TABLE_NAME = "register";
    public final static String COL_Id = "ID";
    public final static String COL_Name = "NAME";
    public final static String COL_Age = "AGE";
    public final static String COL_City = "CITY";
    public final static String COL_Phone = "PHONE";
    public final static String COL_Gmail = "GMAIL";
    public final static String COL_Password = "PASSWORD";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ TABLE_NAME +"(ID INTEGER PRIMARY KEY AUTOINCREMENT , NAME TEXT , AGE TEXT , CITY TEXT, PHONE TEXT, GMAIL TEXT, PASSWORD TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }

    public boolean insertData(UserDataResponse dataResponse)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_Name,dataResponse.getName());
        contentValues.put(COL_Age,dataResponse.getAge());
        contentValues.put(COL_City,dataResponse.getCity());
        contentValues.put(COL_Phone,dataResponse.getPhone());
        contentValues.put(COL_Gmail,dataResponse.getGmail());
        contentValues.put(COL_Password,dataResponse.getPassword());

        Long result = db.insert(TABLE_NAME,null,contentValues);
        db.close();


        if(result==-1)
        {
            return false;
        }
        else {
            return true;
        }

    }

    public ArrayList<UserDataResponse> showData()
    {
        String sel_qry  = "SELECT * FROM "+ TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sel_qry,null);
        ArrayList<UserDataResponse> dataArrayList = new ArrayList<UserDataResponse>();


        while (cursor.moveToNext())
        {
            String id = cursor.getString(0);
            String name = cursor.getString(1);
            String age = cursor.getString(2);
            String city = cursor.getString(3);
            String phone = cursor.getString(4);
            String gmail = cursor.getString(5);
            String pass = cursor.getString(6);

            UserDataResponse dataResponse = new UserDataResponse();

            if(dataResponse!=null) {
                dataResponse.setId(cursor.getString(0));
                dataResponse.setName(cursor.getString(1));
                dataResponse.setAge(cursor.getString(2));
                dataResponse.setCity(cursor.getString(3));
                dataResponse.setPhone(cursor.getString(4));
                dataResponse.setGmail(cursor.getString(5));
                dataResponse.setPassword(cursor.getString(6));

                dataArrayList.add(dataResponse);
            }

        }
           /* if(cursor.moveToFirst())
            {
                do{
                    UserDataResponse dataResponse = new UserDataResponse();
                    if(dataResponse!=null) {
                        dataResponse.setName(cursor.getString(1));
                        dataResponse.setAge(cursor.getString(2));
                        dataResponse.setCity(cursor.getString(3));
                        dataResponse.setPhone(cursor.getString(4));
                        dataResponse.setGmail(cursor.getString(5));
                        dataResponse.setPassword(cursor.getString(6));

                        dataArrayList.add(dataResponse);
                    }
                } while (cursor.moveToNext());
            } */
        return dataArrayList;
    }

  public boolean updateData(UserDataResponse dataResponse)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_Name,dataResponse.getName());
        contentValues.put(COL_Age,dataResponse.getAge());
        contentValues.put(COL_City,dataResponse.getCity());
        contentValues.put(COL_Phone,dataResponse.getPhone());
        contentValues.put(COL_Gmail,dataResponse.getGmail());
        contentValues.put(COL_Password,dataResponse.getPassword());

        db.update(TABLE_NAME,contentValues,"ID =?",new String[] {dataResponse.getId()});
        return true;
    }

    public Integer deleteData(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[] {id});
    }

 /*
    public int deleteData(UserDataResponse dataResponse)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "ID = ?", new String[] {dataResponse.getId()});
        return 1;
    }
    */

    /*
    public boolean CheckIfRecordExists(String phone)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT "+COL_Phone +" FROM "+ TABLE_NAME + " WHERE " + COL_Phone + " =? ", new  String[] {phone});

        if(c.moveToFirst())
        {
            return false;
        }
        else
            return true;
    }
     */

}
