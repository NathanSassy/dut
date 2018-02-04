package com.agicquel.tp2.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

import static com.agicquel.tp2.database.ConstantsDB.*;

/**
 * Created by agicquel on 31/01/18.
 */

public class TaskDataBase extends SQLiteOpenHelper {
    private static final String CREATE_BDD = "CREATE TABLE " + TABLE_TASK + " ("
            + COL_ID + ", " + COL_TITLE + ", "
            + COL_DESCRIPTION + ", " + COL_DURATION + ", "
            + COL_DATE + ");";


    TaskDataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_TASK + ";");
        onCreate(sqLiteDatabase);
    }


}
