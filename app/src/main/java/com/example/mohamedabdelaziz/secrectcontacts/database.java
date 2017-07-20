package com.example.mohamedabdelaziz.secrectcontacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Mohamed Abd ELaziz on 7/20/2017.
 */

public class database extends SQLiteOpenHelper {



    public database(Context context) {
        super(context, "data", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE CONTRACT ( NAME VARCHAR(45) , PHONE VARCHAR(15) PRIMARY KEY )");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS CONTRACT ");
        onCreate(sqLiteDatabase);
    }

    public long add_num(String name ,String phone ) {
        SQLiteDatabase sqLitewrite = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", name);
        contentValues.put("PHONE", phone);
        long newRowId = sqLitewrite.insert("CONTRACT", null, contentValues);
        sqLitewrite.close();
        return newRowId;
    }



    public int delete(String phone) {
        SQLiteDatabase sqLiteread = this.getReadableDatabase();
        String[] array = {phone};
        return sqLiteread.delete("CONTRACT", "PHONE"+ " = ? ", array);
    }

    public ArrayList restore_data() {
        ArrayList<contact> array_values = new ArrayList<>();

        SQLiteDatabase sql = this.getReadableDatabase();
        Cursor cursor = sql.rawQuery("SELECT * FROM " + "CONTRACT", null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            array_values.add(new contact(cursor.getString(cursor.getColumnIndex("NAME")),cursor.getString(cursor.getColumnIndex("PHONE"))));
            cursor.moveToNext();
        }
        return array_values;
    }
}
